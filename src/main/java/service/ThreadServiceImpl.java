package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import dao.ThreadDaoAbstract;
import dao.ThreadDaoImpl;
import model.TableOne;
import model.TableTwo;
import model.Value;
import org.apache.log4j.Logger;
import parser.ObjectParseJSON;


public class ThreadServiceImpl implements ThreadServiceAbstract {
    ThreadDaoAbstract threadDaoAbstract = new ThreadDaoImpl();
    ObjectParseJSON obj = new ObjectParseJSON();
    private static final Logger log = Logger.getLogger(ThreadServiceImpl.class);

    @Override
    public void saveNewEntity(Value value) {
        try {
            threadDaoAbstract.saveNewEntity(value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized int parallelProcessingOfThreads(String nameThread) throws JsonProcessingException, InterruptedException {
        TableOne tableOne = threadDaoAbstract.getEntity();
        log.info("Поток " + nameThread + " получил запись id=" + tableOne.getKey());
        String val = tableOne.getValue();
        Value value = obj.parseJsonToObj(val);
        String[] str = value.getQueue();
        log.info("Поток " + nameThread + " проверяет трогал ли он ранее запись с id=" + tableOne.getKey());
        if (str[0] != null) {//проверяет массив-есть ли там записи
            if (str[0].equals(nameThread)) {
                Thread.sleep(100);
                log.info("Поток " + nameThread + " проверил, что ранее уже трогал эту запись с id=" + tableOne.getKey());

                return 0;
            }
        } else if (str[1] != null) {
            if (str[1].equals(nameThread)) {
                log.info("Поток " + nameThread + " проверил, что ранее уже трогал эту запись с id=" + tableOne.getKey());
                return 0;
            }
        }
        if (value.isThreadA() && value.isThreadB()) {
            log.info("Поток " + nameThread + " проверил, что условия переноса записи соблюдены для id=" + tableOne.getKey());
            threadDaoAbstract.saveExistEntity(mappingOneToTwo(tableOne));
            log.info("Поток " + nameThread + " сохранил запись с id=" + tableOne.getKey() + "в таблицу т2");
            threadDaoAbstract.delete(tableOne);
            log.info("Поток " + nameThread + " удалил запись из таблицы т1 с id=" + tableOne.getKey());
            Thread.sleep(100);
            return 1;
        } else {
            if (str[0] == null) {
                str[0] = nameThread;
                log.info("Поток " + nameThread + " записал свое имя в массив записи с id=" + tableOne.getKey());

            } else {
                str[1] = nameThread;
                log.info("Поток " + nameThread + " записал свое имя в массив записи с id=" + tableOne.getKey());
            }
            value.setQueue(str);
            if (nameThread.equals("threadA")) {
                if (!value.isThreadA()) {
                    value.setThreadA(true);
                    log.info("Поток " + nameThread + " установил флаг=true для записи с id=" + tableOne.getKey());

                }
            } else {
                if (!value.isThreadB()) {
                    value.setThreadB(true);
                    log.info("Поток " + nameThread + " установил флаг=true для записи с id=" + tableOne.getKey());

                }
            }
            tableOne.setValue(obj.parseObjToJson(value));
            threadDaoAbstract.merge(tableOne);
            log.info("Поток " + nameThread + " обновил данные в таблице т1 к записи с id=" + tableOne.getKey());
        }
        Thread.sleep(100);
        return 0;
    }

    @Override
    public TableTwo mappingOneToTwo(TableOne tableOne) {
        return new TableTwo(null, tableOne.getValue());
    }
}

