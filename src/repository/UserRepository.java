package repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import model.Member;

public class UserRepository {
    private final Map<Integer, Member> members = new HashMap<>();

    public void add(Member member){
        members.put(member.getId(), member);
    }

    public Member getById(int id){
        return members.get(id);
    }

    public void remove(int id){
        members.remove(id);
    }

    public Collection<Member> getAll(){
        return members.values();
    }

    public Member findByName(String name){
        for(Member m : members.values())
            if(m.getName().equalsIgnoreCase(name))
                return m;
        return null;
    }






}
