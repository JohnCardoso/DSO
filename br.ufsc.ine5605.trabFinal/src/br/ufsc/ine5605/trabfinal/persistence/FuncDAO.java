package br.ufsc.ine5605.trabfinal.persistence;

import br.ufsc.ine5605.trabfinal.objects.Funcionario;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.HashMap;



public class FuncDAO {
    private HashMap <String, Funcionario> regisFunc;
    private final String fileReg = "funci.txt";
    private static FuncDAO fDAO;
    
    public FuncDAO() {
        super();
        regisFunc = new HashMap<>();
        register();
    }
    
    private void register() {
        
        try {
            FileInputStream fis = new FileInputStream(fileReg);
            ObjectInputStream ois = new ObjectInputStream(fis);

            this.regisFunc = (HashMap<String, Funcionario>) ois.readObject();

            ois.close();
            fis.close();

        } catch (ClassNotFoundException e) {
            System.err.println(e);
        } catch (FileNotFoundException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    
    public void insert(Funcionario func) {
        if(func != null) {
            regisFunc.put(func.getMatricula(), func);
            persist();
        }
    }
    
    public void persist() {
        try {
            FileOutputStream fos = new FileOutputStream(fileReg);
            
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(regisFunc);

            oos.flush();
            fos.flush();

            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    
    public static FuncDAO getFDAO() {
        if (fDAO == null) {
            fDAO = new FuncDAO();
        }

        return fDAO;
    }
    
    public Collection<Funcionario> getList() {
		return regisFunc.values();
	}

    public static void setFDAO(FuncDAO fDAO) {
        FuncDAO.fDAO = fDAO;
    }
    
}
