package main;

import java.io.IOException;

import imagerenamer.ImageRenamer;

public class Main {

	public static final void main(String args[]) throws IOException {
		ImageRenamer renamer = new ImageRenamer();
		renamer.renameImages();
	}
	
}