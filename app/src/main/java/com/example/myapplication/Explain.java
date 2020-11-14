package com.example.myapplication;


import androidx.annotation.NonNull;

public class Explain {
        private String originalWord;

        public String getOriginalWord() {
            return originalWord;
        }

        public void setOriginalWord(String originalWord) {
            this.originalWord = originalWord;
        }

        public String getReplacedWord() {
            return replacedWord;
        }

        public void setReplacedWord(String replacedWord) {
            this.replacedWord = replacedWord;
        }

        public String getExplaination() {
            return explaination;
        }

        public void setExplaination(String explaination) {
            this.explaination = explaination;
        }

        private String replacedWord;
        private String explaination;


    @NonNull
    @Override
    public String toString() {
        return replacedWord.toString()+" =======" + originalWord.toString() + "===\n" + explaination.toString();
    }
}

