package com.example.myapplication;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CorrectedSentence {

    private String orginalSentence;
    private String correctedSentence;
    private Set<String> incorrectWords;
    private Map<Integer,Explain> offsetMap = new HashMap<Integer, Explain>();
    private Map<String,Explain> explainationMap = new HashMap<String, Explain>();

    public String getOrginalSentence() {
        return orginalSentence;
    }

    public void setOrginalSentence(String orginalSentence) {
        this.orginalSentence = orginalSentence;
    }

    public String getCorrectedSentence() {
        return correctedSentence;
    }

    public void setCorrectedSentence(String correctedSentence) {
        this.correctedSentence = correctedSentence;
    }

    public Set<String> getIncorrectWords() {
        return incorrectWords;
    }

    public void setIncorrectWords(Set<String> incorrectWords) {
        this.incorrectWords = incorrectWords;
    }

    public Map<Integer, Explain> getOffsetMap() {
        return offsetMap;
    }

    public void setOffsetMap(Map<Integer, Explain> offsetMap) {
        this.offsetMap = offsetMap;
    }

    public Map<String, Explain> getExplainationMap() {
        return explainationMap;
    }

    public void setExplainationMap(Map<String, Explain> explainationMap) {
        this.explainationMap = explainationMap;
    }



}
