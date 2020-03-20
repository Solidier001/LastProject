package pojo;

public class WithRealpath {
    private Object Entity;

    private String realpath;

    public Object getEntity() {
        return Entity;
    }

    public void setEntity(Object entity) {
        Entity = entity;
    }

    public String getRealpath() {
        return realpath;
    }

    public void setRealpath(String realpath) {
        this.realpath = realpath;
    }

    public WithRealpath(Object entity, String realpath) {
        Entity = entity;
        this.realpath = realpath;
    }
}
