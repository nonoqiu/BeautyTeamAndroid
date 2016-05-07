package company.zzw.john.beautyteam.domain;

import java.util.List;

/**
 * Created by john on 2016/5/5.
 */
public class GroupId {

    public int StatusCode;

    public java.util.List<ListEntity> List;

    public  class ListEntity {
        public int GU_RelationId;
        public int GroupId;
        public String ObisoftUserId;
        public int RelationType;
    }
}
