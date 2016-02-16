/* 
 *  Copyright (C) 2006 by:
 *  Da He <da.he@uni-dortmund.de>
 *  Julian Flake <julian.flake@uni-dortmund.de>
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
 */


#ifndef _CRENDERER_H
#define _CRENDERER_H

#include <iostream>
#include <sys/types.h>
#include <sys/param.h>
#include <sys/wait.h>
#include <errno.h>
#include <dirent.h>
#include <stdio.h>
#include <stdlib.h>
#include <cstdlib>
#include <unistd.h>
#include <fcntl.h>
#include <pthread.h>
#include <sys/wait.h>
//#include <uuid/uuid.h>

#include <upnp/upnp.h>
#include <upnp/upnptools.h>
#include <upnp/ixml.h>
#include <upnp/ithread.h>

#include <string.h>
#include "cService.h"
#include "cConnectionManager.h"
#include "cAVTransport.h"
#include "cRenderingControl.h"

#define DEBUG
//#define DEFAULT_WEB_DIR "/etc/renderer"
#define DESC_URL_SIZE 200
#define	DUP2CLOSE(oldfd, newfd)	(dup2(oldfd, newfd) == 0  &&  close(oldfd) == 0)

using namespace std;

class cRenderer {
private:

	char* web_root;
	enum {
		ConnectionManager,
		AVTransport,
		RenderingControl
	};
	cService *services[3] ;

	// thread
	pthread_t thread;
	int iret;
	#define	PARENT_READ	readpipe[0]
	#define	CHILD_WRITE	readpipe[1]
	#define CHILD_READ	writepipe[0]
	#define PARENT_WRITE	writepipe[1]
	FILE* readfp;
	//pipe file
	int	writepipe[2],	/* parent -> child */
		readpipe [2],	/* child -> parent */
		errpipe[2];
	pid_t childpid;
	// mutex
	ithread_mutex_t rendererMutex;
	ithread_mutex_t outputMutex;
	
	// methods
	static void wakeup(int in);
	IXML_Document* getDescriptionDoc();

	void parseConfigFile();
	void trim(string& str);
	void str_replace(string needle, string replacement, string& haystack);
	void getVarName(string& varName, string& haystack);
	void getValue(string& value, string& haystack);
	char* get_uuid();
	char* generate_uuid();
	void handleOptions(int argc,char* argv[]);
	void initVar();

	void handleActionRequest(Upnp_Action_Request* Event);
	void handleGetVarRequest(Upnp_State_Var_Request* Event);
	void handleSubscriptionRequest(Upnp_Subscription_Request* Event);
	
public:
	
    // global variables
	char* udn;
	char friendlyName[100];
	char xmlIcon[100];
	char* ip_addr;
	char* mplayer_bin;
    char* fullscreen;
	
	
	UpnpDevice_Handle renderer_hdl;
	cRenderer(int argc,char* argv[]);
	~cRenderer();
	static int callbackEventHandler (Upnp_EventType EventType, void *Event, void *Cookie);
	int mypopen(char* uri);
	static void* threadCreate( void *ptr );
	string showOutput();
	char* getXMLValue(IXML_Document *doc, char tagName[]);
	//void setVar(Upnp_Action_Request *Event,int service, int var, char* val);
	char* mplayer_cmd(char command[],bool output);
	void mplayer_start(); 
	void seconds2tstamp(char* out, char* in);
	int time2seconds(char* in);
	bool mplayer_is_running;
	int positionToSeek;

	static void* PositionPercentThreadMethod(void *ptr);
	static void* PositionTimeThreadMethod(void *ptr);
};



#endif /* _CRENDERER_H */
