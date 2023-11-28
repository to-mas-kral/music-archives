package com.tom.musicarchives.model;

import java.util.List;

public interface MemberDAO {
    Member getMemberById(int id);

    void saveMember(Member member);

    void updateMember(Member member);

    void deleteMember(int id);

    List<Member> getAllMembers();
}
