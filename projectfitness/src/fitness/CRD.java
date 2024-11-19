package fitness;

import java.io.IOException;

public abstract class CRD { //template for all classes with add, display, and delete behaviors.
	public abstract void saveToFile() throws IOException;
	public abstract void add() throws IOException;
	public abstract void display() throws IOException;
	public abstract void delete() throws IOException;
}
