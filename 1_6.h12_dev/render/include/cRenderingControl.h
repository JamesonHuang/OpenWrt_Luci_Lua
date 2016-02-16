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


#ifndef _CRENDERINGCONTROL_H_
#define _CRENDERINGCONTROL_H_
#include "cService.h"


class cRenderingControl : public cService
{
	public:
		cRenderingControl(void* lnkcRenderer);
		~cRenderingControl();
		enum vars{
			RC_Mute,
			RC_LastChange,
			RC_A_ARG_TYPE_InstanceID,
			RC_A_ARG_TYPE_PresetName,
			RC_PresetNameList,
			RC_Volume,
			RC_A_ARG_TYPE_Channel
		};
		// Action-Functions
		typedef int(cRenderingControl::*ptr2fct)(IXML_Document* in, IXML_Document** out, Upnp_Action_Request* Event);
		ptr2fct actions[36];
		int callAction(int i, IXML_Document* in, IXML_Document** out, Upnp_Action_Request* Event);
		
		int ListPresets(IXML_Document * in, IXML_Document ** out,Upnp_Action_Request *Event);
		int SelectPreset(IXML_Document * in, IXML_Document ** out,Upnp_Action_Request *Event);
		int GetBrightness(IXML_Document * in, IXML_Document ** out,Upnp_Action_Request *Event);
		int SetBrightness(IXML_Document * in, IXML_Document ** out,Upnp_Action_Request *Event);
		int GetContrast(IXML_Document * in, IXML_Document ** out,Upnp_Action_Request *Event);
		int SetContrast(IXML_Document * in, IXML_Document ** out,Upnp_Action_Request *Event);
		int GetSharpness(IXML_Document * in, IXML_Document ** out,Upnp_Action_Request *Event);
		int Setsharpness(IXML_Document * in, IXML_Document ** out,Upnp_Action_Request *Event);
		int GetRedVideoGain(IXML_Document * in, IXML_Document ** out,Upnp_Action_Request *Event);
		int SetRedVideoGain(IXML_Document * in, IXML_Document ** out,Upnp_Action_Request *Event);
		int GetGreenVideoGain(IXML_Document * in, IXML_Document ** out,Upnp_Action_Request *Event);
		int SetGreenVideoGain(IXML_Document * in, IXML_Document ** out,Upnp_Action_Request *Event);
		int GetBlueVideoGain(IXML_Document * in, IXML_Document ** out,Upnp_Action_Request *Event);
		int SetBlueVideoGain(IXML_Document * in, IXML_Document ** out,Upnp_Action_Request *Event);
		int GetRedVideoBlackLevel(IXML_Document * in, IXML_Document ** out,Upnp_Action_Request *Event);
		int SetRedVideoBlackLevel(IXML_Document * in, IXML_Document ** out,Upnp_Action_Request *Event);
		int GetGreenVideoBlackLevel(IXML_Document * in, IXML_Document ** out,Upnp_Action_Request *Event);
		int SetGreenVideoBlackLevel(IXML_Document * in, IXML_Document ** out,Upnp_Action_Request *Event);
		int GetBlueVideoBlackLevel(IXML_Document * in, IXML_Document ** out,Upnp_Action_Request *Event);
		int SetBlueVideoBlackLevel(IXML_Document * in, IXML_Document ** out,Upnp_Action_Request *Event);
		int GetColorTemperature(IXML_Document * in, IXML_Document ** out,Upnp_Action_Request *Event);
		int SetColorTemperature(IXML_Document * in, IXML_Document ** out,Upnp_Action_Request *Event);
		int GetHorizontalKeystone(IXML_Document * in, IXML_Document ** out,Upnp_Action_Request *Event);
		int SetHorizontalKeystone(IXML_Document * in, IXML_Document ** out,Upnp_Action_Request *Event);
		int GetVerticalKeystone(IXML_Document * in, IXML_Document ** out,Upnp_Action_Request *Event);
		int SetVerticalKeystone(IXML_Document * in, IXML_Document ** out,Upnp_Action_Request *Event);
		int GetMute(IXML_Document * in, IXML_Document ** out,Upnp_Action_Request *Event);
		int SetMute(IXML_Document * in, IXML_Document ** out,Upnp_Action_Request *Event);
		int GetVolume(IXML_Document * in, IXML_Document ** out,Upnp_Action_Request *Event);
		int SetVolume(IXML_Document * in, IXML_Document ** out,Upnp_Action_Request *Event);
		int GetVolumeDB(IXML_Document * in, IXML_Document ** out,Upnp_Action_Request *Event);
		int SetVolumeDB(IXML_Document * in, IXML_Document ** out,Upnp_Action_Request *Event);
		int GetVolumeDBRange(IXML_Document * in, IXML_Document ** out,Upnp_Action_Request *Event);
		int GetLoudness(IXML_Document * in, IXML_Document ** out,Upnp_Action_Request *Event);
		int SetLoudness(IXML_Document * in, IXML_Document ** out,Upnp_Action_Request *Event);
		int SetPlayMode(IXML_Document * in, IXML_Document ** out,Upnp_Action_Request *Event);
		
	
	protected:
		int notificator();

	
};


#endif	//_CRENDERINGCONTROL_H_
