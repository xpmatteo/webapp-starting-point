package it.xpug.todolist;

import java.util.*;
import java.util.function.*;


public class Router {

	static class RouterEntry {
		String path;
		Supplier<Command> command;
		boolean isPostRequired = false;
		boolean isGetRequired = false;
		String nonEmptyParameterRequired;

		public static RouterEntry forPost(String path, Supplier<Command> command) {
			return new RouterEntry(path, command) {{
				this.isPostRequired = true;
			}};
		}

		public static RouterEntry forGet(String path, Supplier<Command> command) {
			return new RouterEntry(path, command) {{
				this.isGetRequired = true;
			}};
		}

		public RouterEntry(String path, Supplier<Command> command) {
	        this.path = path;
	        this.command = command;
        }

		protected RouterEntry withNonEmptyParameter(String name) {
			nonEmptyParameterRequired = name;
			return this;
		}

		@Override
		public String toString() {
		    return String.format(
		    		"%s get required? %s post required? %s parameter required? %s",
		    		this.path,
		    		this.isGetRequired,
		    		this.isPostRequired,
		    		this.nonEmptyParameterRequired
		    		);

		}
	}

	private Supplier<Command> defaultCommand;
	private List<RouterEntry> entries = new ArrayList<RouterEntry>();

	public RouterEntry onAnyMethod(String path, Supplier<Command> command) {
		RouterEntry entry = new RouterEntry(path, command);
		this.entries.add(entry);
		return entry;
    }

	public RouterEntry onGet(String path, Supplier<Command> command) {
		RouterEntry entry = RouterEntry.forGet(path, command);
		this.entries.add(entry);
		return entry;
	}

	public RouterEntry onPost(String path, Supplier<Command> command) {
		RouterEntry entry = RouterEntry.forPost(path, command);
		this.entries.add(entry);
		return entry;
	}

	public void onNotFound(Supplier<Command> command) {
		this.defaultCommand = command;
    }

	public Command getCommandFor(WebRequest request) {
		for (RouterEntry entry : entries) {
			if (matchesMethod(entry, request)
					&& matchesParameters(entry, request)
					&& request.matches(entry.path)) {
				return entry.command.get();
			}
        }
		if (null == defaultCommand)
			throw new RuntimeException("We should have a default command, but it was not set");
		return defaultCommand.get();
    }

	public boolean matchesParameters(RouterEntry entry, WebRequest request) {
	    return null == entry.nonEmptyParameterRequired
	    		|| null != request.getParameter(entry.nonEmptyParameterRequired);
	}

	private boolean matchesMethod(RouterEntry entry, WebRequest request) {
		if (request.isPost())
			return !entry.isGetRequired;

		return !entry.isPostRequired;
    }

}
