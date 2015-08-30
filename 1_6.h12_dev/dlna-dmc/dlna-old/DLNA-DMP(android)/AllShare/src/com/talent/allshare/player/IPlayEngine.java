package com.talent.allshare.player;


public interface IPlayEngine {
	public void exit();
	public void play();
	public void play(int position);
	public void replay();
	public void pause();
	public void stop();
	public void prev();
	public void next();
	public void skipTo(int time);
}
