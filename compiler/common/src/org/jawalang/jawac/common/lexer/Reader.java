package org.jawalang.jawac.common.lexer;

import org.jawalang.jawac.common.util.Chars;

public class Reader implements Chars {

    private final char[] buffer;
    private final int limit;
    private int position;
    private char current;

    public Reader(char[] buffer, int limit) {
        if (buffer == null) throw new IllegalArgumentException("buffer must not be null");
        if (limit < 0 || limit > buffer.length) {
            throw new IllegalArgumentException(
                    "limit must not be negative or greater than buffer length");
        }
        this.buffer = buffer;
        this.limit = limit;
        this.position = 0;
        this.current = '\0';
    }

    public char next() {
        if (position < limit) {
            return current = buffer[position++];
        } else {
            return current = EOI;
        }
    }

    public char peek() {
        return get(position);
    }

    public char peek(int offset) {
        if (offset < 0) throw new IllegalArgumentException("offset must not be negative");
        return get(position + offset);
    }

    public int skip(int offset) {
        if (offset < 0) throw new IllegalArgumentException("offset must not be negative");
        int remaining = remaining();
        if (offset < remaining) {
            position += offset;
            return offset;
        } else {
            position = limit;
            return remaining;
        }
    }

    public char get(int index) {
        if (index < limit) {
            return buffer[index];
        } else {
            return EOI;
        }
    }

    public boolean accept(char ch) {
        if (peek() == ch) {
            position++;
            return true;
        }
        return false;
    }

    public boolean acceptOneOf(char ch1, char ch2) {
        char ch = peek();
        if (ch == ch1 || ch == ch2) {
            position++;
            return true;
        }
        return false;
    }

    public boolean acceptOneOf(char ch1, char ch2, char ch3) {
        char ch = peek();
        if (ch == ch1 || ch == ch2 || ch == ch3) {
            position++;
            return true;
        }
        return false;
    }

    public int remaining() {
        return limit - position;
    }

    public boolean hasRemaining() {
        return position < limit;
    }

    public int limit() {
        return limit;
    }

    public int position() {
        return position;
    }

    public char current() {
        return current;
    }

}
