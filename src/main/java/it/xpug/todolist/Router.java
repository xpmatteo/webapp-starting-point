package it.xpug.todolist;

import java.util.*;


public class Router {

	static class RouterEntry {
		String path;
		Command command;
		boolean isPostRequired = false;
		String nonEmptyParameterRequired;

		public static RouterEntry forPost(String path, Command command) {
			return new RouterEntry(path, command) {{
				this.isPostRequired = true;
			}};
		}

		public RouterEntry(String path, Command command) {
	        this.path = path;
	        this.command = command;
        }

		protected RouterEntry withNonEmptyParameter(String name) {
			nonEmptyParameterRequired = name;
			return this;
		}
	}

	private Command defaultCommand;
	private List<RouterEntry> entries = new ArrayList<RouterEntry>();

	public RouterEntry onAnyMethod(String path, Command command) {
		RouterEntry entry = new RouterEntry(path, command);
		this.entries.add(entry);
		return entry;
    }

	public void onPost(String path, Command command) {
		this.entries.add(RouterEntry.forPost(path, command));
	}

	public void defaultAnswer(Command command) {
		this.defaultCommand = command;
    }

	public Command getCommandFor(WebRequest request) {
		for (RouterEntry entry : entries) {
			if (matchesMethod(entry, request)
					&& matchesParameters(entry, request)
					&& request.matches(entry.path)) {
				return entry.command;
			}
        }
		return defaultCommand;
    }

	private boolean matchesMethod(RouterEntry entry, WebRequest request) {
		return !entry.isPostRequired || entry.isPostRequired && request.isPost();
    }

	public boolean matchesParameters(RouterEntry entry, WebRequest request) {
        return null == entry.nonEmptyParameterRequired
        		|| null != request.getParameter(entry.nonEmptyParameterRequired);
    }

}
