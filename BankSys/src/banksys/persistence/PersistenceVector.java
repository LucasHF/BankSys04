package banksys.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Vector;

import com.thoughtworks.xstream.XStream;

import banksys.account.AbstractAccount;

public class PersistenceVector {
	public static void recordVector(Vector<AbstractAccount> repository) throws FileNotFoundException{
		XStream xStream = new XStream();
		xStream.toXML(repository, new FileOutputStream(System.getProperty("user.home") + File.separator + "Documents" + File.separator + "vectorAccount.temp"));
	}
	public static Vector<AbstractAccount> captureVector() throws FileNotFoundException{
		XStream xStream = new XStream();
		return (Vector<AbstractAccount>)xStream.fromXML(new FileInputStream(System.getProperty("user.home") + File.separator + "Documents" + File.separator + "vectorAccount.temp"));
	}
}
