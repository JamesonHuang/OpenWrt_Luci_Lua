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


#ifndef _CCONNECTIONMANAGER_H_
#define _CCONNECTIONMANAGER_H_
#include "cService.h"
using namespace std;

class cConnectionManager : public cService
{
	public:
		cConnectionManager(void* lnkcRenderer);
		~cConnectionManager();
		enum vars{
			CM_A_ARG_TYPE_ProtocolInfo,
			CM_A_ARG_TYPE_ConnectionStatus,
			CM_A_ARG_TYPE_AVTransportID,
			CM_A_ARG_TYPE_RcsID,
			CM_A_ARG_TYPE_ConnectionID,
			CM_A_ARG_TYPE_ConnectionManager,
			CM_SourceProtocolInfo,
			CM_SinkProtocolInfo,
			CM_A_ARG_TYPE_Direction,
			CM_CurrentConnectionIDs
		};
		// Action-Functions
		typedef int(cConnectionManager::*ptr2fct)(IXML_Document* in, IXML_Document** out, Upnp_Action_Request* Event);
		ptr2fct actions[5];
		int callAction(int i, IXML_Document* in, IXML_Document** out, Upnp_Action_Request* Event);

		int GetProtocolInfo(IXML_Document* in, IXML_Document** out, Upnp_Action_Request* Event);
		int PrepareForConnection(IXML_Document* in, IXML_Document** out, Upnp_Action_Request* Event);
		int ConnectionComplete(IXML_Document* in, IXML_Document** out, Upnp_Action_Request* Event);
		int GetCurrentConnectionIDs(IXML_Document* in, IXML_Document** out, Upnp_Action_Request* Event);
		int GetCurrentConnectionInfo(IXML_Document* in, IXML_Document** out, Upnp_Action_Request* Event);

		
	protected:

};


#endif	//_CCONNECTIONMANAGER_H_
