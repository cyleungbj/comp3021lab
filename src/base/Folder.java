package base;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Folder implements Comparable<Folder>, java.io.Serializable{

	private ArrayList<Note> notes;
	private String name;
	
	public Folder(String name) {
		this.name = name;
		notes = new ArrayList<Note>();
	}
	
	public void addNote(Note note) {
		notes.add(note);
	
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Note> getNotes(){
		return notes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Folder other = (Folder) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	
	@Override
	public String toString() {
		
		int nText = 0;
		int nImage = 0;
		
		for(Note note : notes)
		{
			if(note instanceof TextNote)
			{
				nText++;
			}
			else
			{
				nImage++;
			}
			
		}
		return name+":"+nText+":"+nImage;
		
	}
	
	@Override
	public int compareTo(Folder o){
		
		if(this.getName().compareTo(o.getName()) < 0)
			return -1;
		else if(this.getName().compareTo(o.getName()) > 0)
			return 1;
		else
			return 0;
		
	}

	public void sortNotes() {
		Collections.sort(notes);
	}
	
	
	public List<Note> searchNotes(String keywords){
		
		String[] tokens = keywords.split(" ");
		List<Note> keyword = new ArrayList<Note>();
		
		ArrayList<Integer> operator_index = new ArrayList<Integer>();
		ArrayList<Boolean> check = new ArrayList<Boolean>();
	
		// find all OR/or index and store it to operator_index
		
		for(int i = 0; i < tokens.length; ++i)
		{
			if(tokens[i].equalsIgnoreCase("or"))
			{
				operator_index.add(i);
				
			}
			
		}
		
		for(Note n1 : notes)
		{
			for(int i = 0; i< tokens.length; ++i)
			{
			
				if(operator_index.contains(i+1)) // the next element is or/OR
				{
					
					String temp_title = n1.getTitle();
					
					boolean title_contain_1 = temp_title.toLowerCase().contains(tokens[i].toLowerCase());
					boolean contents_contain_1 = false;
					
					boolean title_contain_2 = temp_title.toLowerCase().contains(tokens[i+2].toLowerCase());
					boolean contents_contain_2 = false;
					
					if(n1 instanceof TextNote)
					{
						String temp_contain = ((TextNote) n1).content;
						contents_contain_1 = temp_contain.toLowerCase().contains(tokens[i].toLowerCase());
						contents_contain_2 = temp_contain.toLowerCase().contains(tokens[i+2].toLowerCase());
						
					}
					
					if((title_contain_1 == true || contents_contain_1 == true) || (title_contain_2 == true || contents_contain_2 == true))
					{
						check.add(true);
					}
					else
					{
						check.add(false);
					}
					
					i = i + 2;
					
				}
				else
				{
					String temp_title = n1.getTitle();
					
					boolean title_contain_1 = temp_title.toLowerCase().contains(tokens[i].toLowerCase());
					boolean contents_contain_1 = false;
					
					if(n1 instanceof TextNote)
					{
						String temp_contain = ((TextNote) n1).content;
						contents_contain_1 = temp_contain.toLowerCase().contains(tokens[i].toLowerCase());
						
					}
					
					if(title_contain_1 == true || contents_contain_1 == true)
					{
						check.add(true);
					}
					else
					{
						check.add(false);
					}
					
					
				}
			}
			
			
			if(!check.contains(false)) // all true
			{
				keyword.add(n1);
			}
			
			
			check.clear();  // clear check for next note
		
		}
		
		return keyword;
	}

}
