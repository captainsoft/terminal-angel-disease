package com.captainsoft.TADtest._muell;



import com.captainsoft.TADr.model.ScrollText;

/**
 * 
 *
 * @author mathias fringes
 */
public class VbInterfaceUpdater implements InterfaceUpdater {
	
	private ScrollText scrollText;
	
	public VbInterfaceUpdater() {
		super();
	}
	
	public void setScrollText(ScrollText scrollText) {
		this.scrollText = scrollText;
	}


	public ScrollText getScrollText() {
		return scrollText;
	}
	
	@Override
	public void scrollTextChange() {		
		
	}

}
