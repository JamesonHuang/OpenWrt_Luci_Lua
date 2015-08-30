// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package ch.qos.logback.core.pattern.parser;


// Referenced classes of package ch.qos.logback.core.pattern.parser:
//            SimpleKeywordNode, Node

public class CompositeNode extends SimpleKeywordNode
{

    CompositeNode(String s)
    {
        super(2, s);
    }

    public boolean equals(Object obj)
    {
        boolean flag;
        flag = false;
        break MISSING_BLOCK_LABEL_2;
        while(true) 
        {
            do
                return flag;
            while(!super.equals(obj) || !(obj instanceof CompositeNode));
            CompositeNode compositenode = (CompositeNode)obj;
            if(childNode != null)
                flag = childNode.equals(compositenode.childNode);
            else
            if(compositenode.childNode == null)
                flag = true;
        }
    }

    public Node getChildNode()
    {
        return childNode;
    }

    public int hashCode()
    {
        return super.hashCode();
    }

    public void setChildNode(Node node)
    {
        childNode = node;
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        if(childNode != null)
            stringbuffer.append((new StringBuilder()).append("CompositeNode(").append(childNode).append(")").toString());
        else
            stringbuffer.append("CompositeNode(no child)");
        stringbuffer.append(printNext());
        return stringbuffer.toString();
    }

    Node childNode;
}
