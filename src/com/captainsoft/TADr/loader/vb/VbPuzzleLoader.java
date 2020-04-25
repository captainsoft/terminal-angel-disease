/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.loader.vb;

import java.io.FileNotFoundException;

import com.captainsoft.TADr.TadLang;
import com.captainsoft.TADr.engine.excp.GameDataIoException;
import com.captainsoft.TADr.loader.PuzzleLoader;
import com.captainsoft.TADr.loader.vbFile.VbFile;
import com.captainsoft.TADr.model.puzzle.PuzzleFileData;
import com.captainsoft.TADr.model.puzzle.book.Book;
import com.captainsoft.TADr.model.puzzle.event.Event;
import com.captainsoft.TADr.model.puzzle.talk.TalkPage;
import com.captainsoft.spark.files.FileUtils;
import com.captainsoft.spark.utils.ExcUtils;

/**
 * Puzzle loader for the old VB file format.
 *
 * @author mathias fringes
 */
public final class VbPuzzleLoader implements PuzzleLoader {

    // constants

    private static final String TalkFilename = "dat/talk.dat";
    private static final int TalkChunkSize = 99;
    private static final int TalkRecordSize = 286;

    // constructors

    public VbPuzzleLoader() {
        super();
    }

    // public 

    public TalkPage loadTalkPage(String filename, int map, int id) {
        TalkPage tp = new TalkPage();
        VbFile file = null;
        try {
            file = new VbFile(filename, VbFile.R);
            file.setRecordSize(TalkRecordSize);
            file.setChunkSize(TalkChunkSize);
            file.seekRecord(map, id);
            //
            tp.id = id;
            tp.name = file.readEncodedString(30);
            tp.text = file.readEncodedString(250);
            tp.portraitId = file.readByte();
            if (tp.portraitId == 0) {
                tp.portraitId = 100;
            }
            tp.nextTalkPageNr = file.readByte();
            tp.showBullet = file.readByte();
            tp.itemId = file.readByte();
            tp.eventId1 = file.readByte();
            tp.eventId2 = file.readByte();
        } catch (Exception e) {
            throw new GameDataIoException("error loading talk id " + id + " on map " + map, e);
        } finally {
            FileUtils.close(file);
        }
        return tp;
    }

    public void saveTalkPage(int map, TalkPage talkPage) {
        saveTalkPage(TadLang.file(TalkFilename), map, talkPage);
    }

    public void saveTalkPage(String filename, int mapNr, TalkPage talkPage) {
        ExcUtils.argPlusPositive("TalkPage.id", talkPage.id);
        VbFile file = null;
        try {
            file = new VbFile(filename, VbFile.RW);
            file.setRecordSize(TalkRecordSize);
            file.setChunkSize(TalkChunkSize);
            file.seekRecord(mapNr, talkPage.id);
            //
            file.writeEncodedString(talkPage.name, 30);
            file.writeEncodedString(talkPage.text, 250);
            file.writeByte(talkPage.portraitId);
            file.writeByte(talkPage.nextTalkPageNr);
            file.writeByte(talkPage.showBullet);
            file.writeByte(talkPage.itemId);
            file.writeByte(talkPage.eventId1);
            file.writeByte(talkPage.eventId2);
        } catch (Exception e) {
            throw new GameDataIoException("error loading talk id " + talkPage.id + " on map " + mapNr, e);
        } finally {
            FileUtils.close(file);
        }
    }

    public VbFile createBookFile(String type) throws FileNotFoundException {
        VbFile file = new VbFile(TadLang.file("dat/books.dat"), type);
        file.setRecordSize(300);
        file.setChunkSize(1);
        return file;
    }

    public void saveBook(Book book) {
        ExcUtils.argPlusPositive("book.startPage", book.startPage);
        VbFile file = null;
        try {
            file = createBookFile(VbFile.RW);
            file.seekRecord(1, book.startPage);
            //
            file.writeEncodedString(book.text, 299);
            file.writeByte(book.nextPage);
        } catch (Exception e) {
            throw new GameDataIoException("error saving: book " + book + " file: " + file, e);
        } finally {
            FileUtils.close(file);
        }
    }

    // PuzzleLoader

    @Override
    public Book loadBook(int id) {
        Book book = new Book();
        VbFile file = null;
        try {
            file = createBookFile(VbFile.R);
            file.seekRecord(1, id);
            //
            book.startPage = id;
            book.text = file.readEncodedString(299);
            book.nextPage = file.readByte();
        } catch (Exception e) {
            throw new GameDataIoException("error loading: " + file, e);
        } finally {
            FileUtils.close(file);
        }
        return book;
    }

    @Override
    public Event loadEvent(int map, int id) {
        Event event = null;
        VbFile file = null;
        try {
            file = new VbFile(TadLang.file("dat/events.dat"), VbFile.R);
            file.setRecordSize(7);
            file.setChunkSize(99);
            file.seekRecord(map, id);
            //
            int[] data = new int[7];
            for (int i = 0; i < 7; i++) {
                data[i] = file.readByte();
            }
            event = new Event(data);
        } catch (Exception e) {
            throw new GameDataIoException("Error loading: " + file, e);
        } finally {
            FileUtils.close(file);
        }
        return event;
    }

    @Override
    public PuzzleFileData loadPuzzle(int map, int id) {
        PuzzleFileData data = null;
        VbFile file = null;
        try {
            file = new VbFile(TadLang.file("dat/puzzles.dat"), VbFile.R);
            file.setRecordSize(10);
            file.setChunkSize(199);
            file.seekRecord(map, id);
            //
            data = new PuzzleFileData();
            for (int i = 0; i < 10; i++) {
                data.value(i, file.readByte());
            }
        } catch (Exception e) {
            throw new GameDataIoException("Error loading: " + file, e);
        } finally {
            FileUtils.close(file);
        }
        return data;
    }

    @Override
    public TalkPage loadTalkPage(int map, int id) {
        return loadTalkPage(TadLang.file(TalkFilename), map, id);
    }

}
