/**
 * Filename: Attributes.java.
 * 
 * @author Jon Beare, Dylon McGrann, Greg Sinclair, Cole Stout, Benedikt Wagenlehner
 * Course: CSCI 420 (Section 01) 
 * Description: Attributes class.
 * 
 */



package velcro;

public class Attributes {
	String name;
	String parentClass;
	
	// Attributes constructor.
	Attributes(String name) {
		this.name = name;
	}
	
	// Returns Attributes name.
	public String getName() {
		return name;
	}

	
	// Returns boolean of whether input Attributes object equals this Attributes object.
	public boolean equals(Attributes input) {
		return (input.name.equals(this.name));
	}
	
	// Renames Attribute.
	public boolean rename(String newName) {
		if (newName.equals(""))
			return false;
		this.name = newName;
		return true;
	}
	
}