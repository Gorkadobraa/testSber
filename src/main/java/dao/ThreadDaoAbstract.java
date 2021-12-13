package dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import model.TableOne;
import model.TableTwo;
import model.Value;


public interface ThreadDaoAbstract {
     void saveNewEntity(Value value) throws JsonProcessingException;
     void saveExistEntity(TableTwo tableTwo);
     void delete (TableOne tableOne);
     void merge (TableOne tableOne);
     TableOne getEntity ();
}
