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


#ifndef _CSERVICE_H_
#define _CSERVICE_H_

#include <iostream>
#include <stdarg.h>
#include <pthread.h>
#include <upnp/upnp.h>
#include <upnp/upnptools.h>
#include <upnp/ixml.h>

using namespace std;
class cService
{
	public:
		cService(void* lnkcRenderer);
		virtual ~cService();
	
		char* service_id;
	
		int retForpositionPercent;
		int retForpositionTime;
		pthread_t positionPercentThread;
		pthread_t positionTimeThread;
	
		enum{
			CM_id,
			AV_id,
			RC_id
		};	
		void* lnkcRenderer;	
	
		// Variables
		int var_count;
		char* name[50];
		char* value[50];

		// Actions
		typedef int (cService::*ptr2fct)(IXML_Document* in, IXML_Document** out, Upnp_Action_Request* Event);
		ptr2fct actions[5];
		int action_count;
		char* action_name[50];
		virtual int callAction(int i, IXML_Document* in, IXML_Document** out, Upnp_Action_Request* Event);
		char* getXMLValue(IXML_Document *doc, char tagName[]);
		void setVar(int service, int count, ...);

		char* getTrackLength();
	
	protected:
	
};


#endif	//_CSERVICE_H_
