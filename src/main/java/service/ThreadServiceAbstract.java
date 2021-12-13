package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import model.TableOne;
import model.TableTwo;
import model.Value;

public interface ThreadServiceAbstract {
    void saveNewEntity(Value value) throws JsonProcessingException;

    int parallelProcessingOfThreads(String nameThread) throws JsonProcessingException, InterruptedException;

    TableTwo mappingOneToTwo(TableOne tableOne);
}
