package org.hbrs.se.ws20.uebung3.persistence;


import org.hbrs.se.ws20.uebung2.Member;

import java.util.List;

public class MemberView {
    public void dump(List<Member> liste){
            for (Member a: liste) {
                System.out.println(a.toString());
            }
        }
}
