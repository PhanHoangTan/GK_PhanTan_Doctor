package org.entity;

import java.time.LocalDate;

public class Treatment {
    private LocalDate startDate;
    private LocalDate endDate;
    private String diagnosis;

    public Treatment(LocalDate startDate, LocalDate endDate, String diagnosis) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.diagnosis = diagnosis;
    }

    public Treatment() {
    }

    @Override
    public String toString() {
        return "Treatment{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", diagnosis='" + diagnosis + '\'' +
                '}';
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
}
