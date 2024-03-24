package ru.gb.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.aspects.TrackUserAction;
import ru.gb.model.Act;
import ru.gb.repositories.ActRepository;
import ru.gb.service.ActService;

import java.util.List;

/**
 * Сервис заметок для передачи запросов в репозиторий
 */
@Service
@RequiredArgsConstructor
public class ActServiceImpl implements ActService {
    private final ActRepository actRepository;

    /**
     * Метод получения списка всех задач
     *
     * @return список заметок
     */
    @TrackUserAction
    @Override
    public List<Act> getAllActs() {
        return actRepository.findAll();
    }

    /**
     * Метод сохранения заметки
     *
     * @param act новая заметка
     * @return сохраненная заметка
     */
    @TrackUserAction
    @Override
    public Act createAct(Act act) {
        return actRepository.save(act);
    }

    /**
     * Метод получения заметки по id
     *
     * @param id id заметки
     * @return искомая заметка
     */
    @TrackUserAction
    @Override
    public Act getActById(Long id) {
        return actRepository.findById(id).orElseThrow(null);
    }

    /**
     * Метод изменения заметки по id
     *
     * @return обновленная заметка
     */
    @TrackUserAction
    @Override
    public Act updateAct(Act act) {
        return actRepository.save(act);
    }

    /**
     * Метод удаления заметки по id
     *
     * @param id id заметки
     */
    @TrackUserAction
    @Override
    public void deleteAct(Long id) {
        Act actById = getActById(id);
        actRepository.delete(actById);
    }

    @TrackUserAction
    @Override
    public List<Act> findActByReportingPeriod(String reportingPeriod){
        return actRepository.findActByReportingPeriod(reportingPeriod);
    }

}
