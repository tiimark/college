package com.mark.college.service;

import com.mark.college.entity.Absence;
import com.mark.college.entity.Student;
import com.mark.college.entity.Subject;
import com.mark.college.repository.AbsenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Tiffany Mark on 28/06/2016.
 */

@Service
public class AbsenceService {

    @Autowired
    private AbsenceRepository absenceRepository;

    public List<Absence> findAbsences(Student student){
        List<Absence> absences = absenceRepository.findByStudent(student);

        if(!absences.isEmpty())
            return absences;
        return null;
    }

    public Boolean insertAbsences(Absence absence){
        Absence newAbsence = absenceRepository.save(absence);

        if(newAbsence != null)
            return true;
        return false;
    }

    public Absence findAbsence(Subject subject, Student student){
        Absence absence = absenceRepository.findBySubjectAndStudent(subject, student);

        if(absence != null)
            return absence;
        return null;
    }

}
