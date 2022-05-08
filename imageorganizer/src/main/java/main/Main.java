package main;

import dirchooser.DirChooser;
import imagerenamer.ImageRenamer;

public class Main {

	public static final void main(String args[]) {
		new DirChooser(ImageRenamer.newImageRenamer());
	}
	
}