package org.hbrs.se.ws20.uebung2;


import java.util.LinkedList;

public class Container {

    LinkedList<Member> list;
    public Container(){
         list = new LinkedList<>();
    }
    public void addMember(Member member) throws ContainerException {
        for (Member a : list) {
            if (a.getID().equals(member.getID()))
                throw new ContainerException("Das Member-Objekt mit der ID " + a.getID() + " ist bereits vorhanden!");
        }
        list.add(member);
    }
    public String deleteMember(Integer id){
        String erg;
        for (int i = 0; i < list.size(); i++) {
            if (list.listIterator().next().getID().equals(id)) {
                erg = "" + list.get(i).toString();
                list.remove(i);
                return erg;
            }
        }

        return "Die Zahl mit der ID " + id + " ist nicht vorhanden"; // Bei Exception kann man Stacktrace nachvollziehen. Das ist bei einem String nicht möglich
    }
    public  void dump(){
        for (Member a: list) {
            System.out.println(a.toString());
        }
    }
    public int size(){
        return list.size();
    }
}
