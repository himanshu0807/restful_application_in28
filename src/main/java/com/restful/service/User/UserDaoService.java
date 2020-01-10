package com.restful.service.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	private static List<User> al = new ArrayList<User>();
	int userCounter =3;
	static {
		al.add(new User("Abhishek", 1, new Date()));
		al.add(new User("Bhanu", 2, new Date()));
		al.add(new User("Kuldeep", 3, new Date()));
	}
	
	public List<User> findAll()
	{
		return al;
	}
	
	public User saveUser(User user)
	{
		if(user.getId()==null)
		{
			user.setId(++userCounter);
		}
		al.add(user);
		return user;
	}
	public User findUser(int id){
		for (User i : al)
		{
			if (i.getId() == id)
			{
				return i;
			}
		}
		return new User("not present", null, null);
	}
	
	public User deleteUser(int id)
	{
		Iterator<User> user = al.iterator();
		while (user.hasNext())
		{
			User s = user.next();
			if(s.getId() == id)
			{
				user.remove();
				return s;
			}
		}		
			return null;
						
		}
}
