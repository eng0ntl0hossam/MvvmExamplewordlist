package com.example.mvvmexamplewordlist.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.mvvmexamplewordlist.database.Word;
import com.example.mvvmexamplewordlist.database.WordDao;
import com.example.mvvmexamplewordlist.database.WordRoomDatabase;

import java.util.List;

public class WordRepository {

    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;



    public WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert (Word word) {
        new InsertAsyncTask(mWordDao).execute(word);
    }

    public void deleteAll () {
        new DeleteAllAsyncTask(mWordDao).execute();
    }
    public void deleteWord(Word  word) {
        new DeleteWordAsyncTask(mWordDao).execute(word);
    }
    public void update(Word word)  {
        new updateWordAsyncTask(mWordDao).execute(word);
    }

    private static class InsertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        InsertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
    private static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {

        private WordDao mAsyncTaskDao;

        DeleteAllAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }



        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }
    private static class DeleteWordAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        DeleteWordAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }




        @Override
        protected Void doInBackground(Word... words) {
            mAsyncTaskDao.deleteWord(words[0]);
            return null;
        }
    }
    private static class updateWordAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao mAsyncTaskDao;

        updateWordAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }
}