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


#ifndef _CAVTRANSPORT_H_
#define _CAVTRANSPORT_H_
#include "cService.h"


class cAVTransport : public cService
{
	public:
		cAVTransport(void* lnkcRenderer);
		~cAVTransport();
		enum vars {
			AV_CurrentPlayMode,
			AV_RecordStorageMedium,
			AV_LastChange,
			AV_RelativeTimePosition,
			AV_CurrentTrackURI,
	
			AV_CurrentTrackDuration,
			AV_CurrentRecordQualityMode,
			AV_CurrentMediaDuration,
			AV_AbsoluteCounterPosition,
			AV_RelativeCounterPosition,
	
			AV_A_ARG_TYPE_InstanceID,
			AV_AVTransportURI,
			AV_TransportState,
			AV_CurrentTrackMetaData,
			AV_NextAVTransportURI,
	
			AV_PossibleRecordQualityModes,
			AV_AbsoluteTimePosition,
			AV_CurrentTrack,
			AV_NextAVTransportURIMetaData,
			AV_PlaybackStorageMedium,
	
			AV_CurrentTransportActions,
			AV_RecordMediumWriteStatus,
			AV_PossiblePlaybackStorageMedia,
			AV_AVTransportURIMetaData,
			AV_NumberOfTracks,
			
			AV_A_ARG_TYPE_SeekMode,
			AV_A_ARG_TYPE_SeekTarget,
			AV_PossibleRecordStorageMedia,
			AV_TransportStatus,
			AV_TransportPlaySpeed
		};
		// Action-Functions
		typedef int (cAVTransport::*ptr2fct)(IXML_Document* in, IXML_Document** out, Upnp_Action_Request* Event);
		ptr2fct actions[17];
		int callAction(int i,IXML_Document* in, IXML_Document** out, Upnp_Action_Request* Event);

		int SetAVTransportURI(IXML_Document* in, IXML_Document** out, Upnp_Action_Request* Event);
		int SetNextAVTransportURI(IXML_Document* in, IXML_Document** out, Upnp_Action_Request* Event);
		int GetMediaInfo(IXML_Document* in, IXML_Document** out, Upnp_Action_Request* Event);
		int GetTransportInfo(IXML_Document* in, IXML_Document** out, Upnp_Action_Request* Event);
		int GetPositionInfo(IXML_Document* in, IXML_Document** out, Upnp_Action_Request* Event);
		int GetDeviceCapabilities(IXML_Document* in, IXML_Document** out, Upnp_Action_Request* Event);
		int GetTransportSettings(IXML_Document* in, IXML_Document** out, Upnp_Action_Request* Event);
		int Stop(IXML_Document* in, IXML_Document** out, Upnp_Action_Request* Event);
		int Play(IXML_Document* in, IXML_Document** out, Upnp_Action_Request* Event);
		int Pause(IXML_Document* in, IXML_Document** out, Upnp_Action_Request* Event);
		int Record(IXML_Document* in, IXML_Document** out, Upnp_Action_Request* Event);
		int Seek(IXML_Document* in, IXML_Document** out, Upnp_Action_Request* Event);
		int Next(IXML_Document* in, IXML_Document** out, Upnp_Action_Request* Event);
		int Previous(IXML_Document* in, IXML_Document** out, Upnp_Action_Request* Event);
		int SetPlayMode(IXML_Document* in, IXML_Document** out, Upnp_Action_Request* Event);
		int SetRecordQualityMode(IXML_Document* in, IXML_Document** out, Upnp_Action_Request* Event);
		int GetCurrentTransportActions(IXML_Document* in, IXML_Document** out, Upnp_Action_Request* Event);




	protected:
	
};


#endif	//_CAVTRANSPORT_H_
