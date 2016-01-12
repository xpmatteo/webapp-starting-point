package it.xpug.todolist;

import java.io.*;

public abstract class Command {
	public abstract void service() throws IOException;
}
