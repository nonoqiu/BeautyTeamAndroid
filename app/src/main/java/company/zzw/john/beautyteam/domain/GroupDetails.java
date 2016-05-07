package company.zzw.john.beautyteam.domain;

import java.util.List;

/**
 * Created by john on 2016/5/6.
 */
public class GroupDetails {


    public ObjectEntity Object;

    public int StatusCode;

    public static class ObjectEntity {
        public boolean CanNotBeSearched;
        public String CreateTime;
        public String GroupDescription;
        public int GroupId;
        public String GroupName;
        public int GroupType;
        public String Type;

        public List<GURelationsEntity> GU_Relations;

        public static class GURelationsEntity {
            public int GU_RelationId;
            public int GroupId;
            public String ObisoftUserId;
            public int RelationType;
        }
    }
}
