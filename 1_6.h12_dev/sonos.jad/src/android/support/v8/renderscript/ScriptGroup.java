// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v8.renderscript;

import java.util.ArrayList;

// Referenced classes of package android.support.v8.renderscript:
//            BaseObj, RenderScript, RSIllegalArgumentException, Allocation, 
//            RSInvalidStateException, RSRuntimeException, Type, Script

public class ScriptGroup extends BaseObj
{
    public static final class Builder
    {

        private Node findNode(Script.KernelID kernelid)
        {
            int i = 0;
_L6:
            Node node;
            int j;
            if(i >= mNodes.size())
                break MISSING_BLOCK_LABEL_67;
            node = (Node)mNodes.get(i);
            j = 0;
_L5:
            if(j >= node.mKernels.size()) goto _L2; else goto _L1
_L1:
            if(kernelid != node.mKernels.get(j)) goto _L4; else goto _L3
_L3:
            return node;
_L4:
            j++;
              goto _L5
_L2:
            i++;
              goto _L6
            node = null;
              goto _L3
        }

        private Node findNode(Script script)
        {
            int i = 0;
_L3:
            if(i >= mNodes.size())
                break MISSING_BLOCK_LABEL_51;
            if(script != ((Node)mNodes.get(i)).mScript) goto _L2; else goto _L1
_L1:
            Node node = (Node)mNodes.get(i);
_L4:
            return node;
_L2:
            i++;
              goto _L3
            node = null;
              goto _L4
        }

        private void mergeDAGs(int i, int j)
        {
            for(int k = 0; k < mNodes.size(); k++)
                if(((Node)mNodes.get(k)).dagNumber == j)
                    ((Node)mNodes.get(k)).dagNumber = i;

        }

        private void validateCycle(Node node, Node node1)
        {
            for(int i = 0; i < node.mOutputs.size(); i++)
            {
                ConnectLine connectline = (ConnectLine)node.mOutputs.get(i);
                if(connectline.mToK != null)
                {
                    Node node3 = findNode(connectline.mToK.mScript);
                    if(node3.equals(node1))
                        throw new RSInvalidStateException("Loops in group not allowed.");
                    validateCycle(node3, node1);
                }
                if(connectline.mToF == null)
                    continue;
                Node node2 = findNode(connectline.mToF.mScript);
                if(node2.equals(node1))
                    throw new RSInvalidStateException("Loops in group not allowed.");
                validateCycle(node2, node1);
            }

        }

        private void validateDAG()
        {
            for(int i = 0; i < mNodes.size(); i++)
            {
                Node node = (Node)mNodes.get(i);
                if(node.mInputs.size() != 0)
                    continue;
                if(node.mOutputs.size() == 0 && mNodes.size() > 1)
                    throw new RSInvalidStateException("Groups cannot contain unconnected scripts");
                validateDAGRecurse(node, i + 1);
            }

            int j = ((Node)mNodes.get(0)).dagNumber;
            for(int k = 0; k < mNodes.size(); k++)
                if(((Node)mNodes.get(k)).dagNumber != j)
                    throw new RSInvalidStateException("Multiple DAGs in group not allowed.");

        }

        private void validateDAGRecurse(Node node, int i)
        {
            if(node.dagNumber != 0 && node.dagNumber != i)
            {
                mergeDAGs(node.dagNumber, i);
            } else
            {
                node.dagNumber = i;
                int j = 0;
                while(j < node.mOutputs.size()) 
                {
                    ConnectLine connectline = (ConnectLine)node.mOutputs.get(j);
                    if(connectline.mToK != null)
                        validateDAGRecurse(findNode(connectline.mToK.mScript), i);
                    if(connectline.mToF != null)
                        validateDAGRecurse(findNode(connectline.mToF.mScript), i);
                    j++;
                }
            }
        }

        public Builder addConnection(Type type, Script.KernelID kernelid, Script.FieldID fieldid)
        {
            if(mT != null)
            {
                mT.addConnection(type, kernelid, fieldid);
            } else
            {
                Node node = findNode(kernelid);
                if(node == null)
                    throw new RSInvalidStateException("From script not found.");
                Node node1 = findNode(fieldid.mScript);
                if(node1 == null)
                    throw new RSInvalidStateException("To script not found.");
                ConnectLine connectline = new ConnectLine(type, kernelid, fieldid);
                mLines.add(new ConnectLine(type, kernelid, fieldid));
                node.mOutputs.add(connectline);
                node1.mInputs.add(connectline);
                validateCycle(node, node);
            }
            return this;
        }

        public Builder addConnection(Type type, Script.KernelID kernelid, Script.KernelID kernelid1)
        {
            if(mT != null)
            {
                mT.addConnection(type, kernelid, kernelid1);
            } else
            {
                Node node = findNode(kernelid);
                if(node == null)
                    throw new RSInvalidStateException("From script not found.");
                Node node1 = findNode(kernelid1);
                if(node1 == null)
                    throw new RSInvalidStateException("To script not found.");
                ConnectLine connectline = new ConnectLine(type, kernelid, kernelid1);
                mLines.add(new ConnectLine(type, kernelid, kernelid1));
                node.mOutputs.add(connectline);
                node1.mInputs.add(connectline);
                validateCycle(node, node);
            }
            return this;
        }

        public Builder addKernel(Script.KernelID kernelid)
        {
            if(mT == null) goto _L2; else goto _L1
_L1:
            mT.addKernel(kernelid);
_L4:
            return this;
_L2:
            if(mLines.size() != 0)
                throw new RSInvalidStateException("Kernels may not be added once connections exist.");
            if(findNode(kernelid) == null)
            {
                mKernelCount = 1 + mKernelCount;
                Node node = findNode(kernelid.mScript);
                if(node == null)
                {
                    node = new Node(kernelid.mScript);
                    mNodes.add(node);
                }
                node.mKernels.add(kernelid);
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        public ScriptGroup create()
        {
            Object obj;
            if(mT != null)
            {
                obj = mT.create();
            } else
            {
                if(mNodes.size() == 0)
                    throw new RSInvalidStateException("Empty script groups are not allowed");
                for(int i = 0; i < mNodes.size(); i++)
                    ((Node)mNodes.get(i)).dagNumber = 0;

                validateDAG();
                ArrayList arraylist = new ArrayList();
                ArrayList arraylist1 = new ArrayList();
                int ai[] = new int[mKernelCount];
                int j = 0;
                for(int k = 0; k < mNodes.size(); k++)
                {
                    Node node = (Node)mNodes.get(k);
                    for(int l1 = 0; l1 < node.mKernels.size();)
                    {
                        Script.KernelID kernelid = (Script.KernelID)node.mKernels.get(l1);
                        int i2 = j + 1;
                        ai[j] = kernelid.getID(mRS);
                        boolean flag = false;
                        boolean flag1 = false;
                        for(int j2 = 0; j2 < node.mInputs.size(); j2++)
                            if(((ConnectLine)node.mInputs.get(j2)).mToK == kernelid)
                                flag = true;

                        for(int k2 = 0; k2 < node.mOutputs.size(); k2++)
                            if(((ConnectLine)node.mOutputs.get(k2)).mFrom == kernelid)
                                flag1 = true;

                        if(!flag)
                            arraylist.add(new IO(kernelid));
                        if(!flag1)
                            arraylist1.add(new IO(kernelid));
                        l1++;
                        j = i2;
                    }

                }

                if(j != mKernelCount)
                    throw new RSRuntimeException("Count mismatch, should not happen.");
                int ai1[] = new int[mLines.size()];
                int ai2[] = new int[mLines.size()];
                int ai3[] = new int[mLines.size()];
                int ai4[] = new int[mLines.size()];
                for(int l = 0; l < mLines.size(); l++)
                {
                    ConnectLine connectline = (ConnectLine)mLines.get(l);
                    ai1[l] = connectline.mFrom.getID(mRS);
                    if(connectline.mToK != null)
                        ai2[l] = connectline.mToK.getID(mRS);
                    if(connectline.mToF != null)
                        ai3[l] = connectline.mToF.getID(mRS);
                    ai4[l] = connectline.mAllocationType.getID(mRS);
                }

                int i1 = mRS.nScriptGroupCreate(ai, ai1, ai2, ai3, ai4);
                if(i1 == 0)
                    throw new RSRuntimeException("Object creation error, should not happen.");
                RenderScript renderscript = mRS;
                obj = new ScriptGroup(i1, renderscript);
                IO aio[] = new IO[arraylist1.size()];
                obj.mOutputs = aio;
                for(int j1 = 0; j1 < arraylist1.size(); j1++)
                    ((ScriptGroup) (obj)).mOutputs[j1] = (IO)arraylist1.get(j1);

                IO aio1[] = new IO[arraylist.size()];
                obj.mInputs = aio1;
                int k1 = 0;
                while(k1 < arraylist.size()) 
                {
                    ((ScriptGroup) (obj)).mInputs[k1] = (IO)arraylist.get(k1);
                    k1++;
                }
            }
            return ((ScriptGroup) (obj));
        }

        private int mKernelCount;
        private ArrayList mLines;
        private ArrayList mNodes;
        private RenderScript mRS;
        private ScriptGroupThunker.Builder mT;

        public Builder(RenderScript renderscript)
        {
            mNodes = new ArrayList();
            mLines = new ArrayList();
            if(RenderScript.isNative)
                mT = new ScriptGroupThunker.Builder(renderscript);
            mRS = renderscript;
        }
    }

    static class Node
    {

        int dagNumber;
        ArrayList mInputs;
        ArrayList mKernels;
        Node mNext;
        ArrayList mOutputs;
        Script mScript;

        Node(Script script)
        {
            mKernels = new ArrayList();
            mInputs = new ArrayList();
            mOutputs = new ArrayList();
            mScript = script;
        }
    }

    static class ConnectLine
    {

        Type mAllocationType;
        Script.KernelID mFrom;
        Script.FieldID mToF;
        Script.KernelID mToK;

        ConnectLine(Type type, Script.KernelID kernelid, Script.FieldID fieldid)
        {
            mFrom = kernelid;
            mToF = fieldid;
            mAllocationType = type;
        }

        ConnectLine(Type type, Script.KernelID kernelid, Script.KernelID kernelid1)
        {
            mFrom = kernelid;
            mToK = kernelid1;
            mAllocationType = type;
        }
    }

    static class IO
    {

        Allocation mAllocation;
        Script.KernelID mKID;

        IO(Script.KernelID kernelid)
        {
            mKID = kernelid;
        }
    }


    ScriptGroup(int i, RenderScript renderscript)
    {
        super(i, renderscript);
    }

    public void execute()
    {
        mRS.nScriptGroupExecute(getID(mRS));
    }

    public void setInput(Script.KernelID kernelid, Allocation allocation)
    {
        for(int i = 0; i < mInputs.length; i++)
            if(mInputs[i].mKID == kernelid)
            {
                mInputs[i].mAllocation = allocation;
                mRS.nScriptGroupSetInput(getID(mRS), kernelid.getID(mRS), mRS.safeID(allocation));
                return;
            }

        throw new RSIllegalArgumentException("Script not found");
    }

    public void setOutput(Script.KernelID kernelid, Allocation allocation)
    {
        for(int i = 0; i < mOutputs.length; i++)
            if(mOutputs[i].mKID == kernelid)
            {
                mOutputs[i].mAllocation = allocation;
                mRS.nScriptGroupSetOutput(getID(mRS), kernelid.getID(mRS), mRS.safeID(allocation));
                return;
            }

        throw new RSIllegalArgumentException("Script not found");
    }

    IO mInputs[];
    IO mOutputs[];
}
