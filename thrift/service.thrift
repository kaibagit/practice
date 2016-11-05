namespace java com.luna.demo.service

enum CODE {
    SUCCESS = 0;
    FAIL = -1;
}
exception BizException {
    1:required CODE code;
    2:required string msg;
}

struct Member {
    1:required i64 id;
    2:required string username;
    3:i32 status;
}

service MemberService{
    Member findById(1:i64 id);
    void create(1:Member new_member) throws(1:BizException e);
}