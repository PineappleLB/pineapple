package club.pinea.model;

public class ShareFile {
    private String sharfileid;

    private Integer fileid;

    public String getSharfileid() {
        return sharfileid;
    }

    public void setSharfileid(String sharfileid) {
        this.sharfileid = sharfileid == null ? null : sharfileid.trim();
    }

    public Integer getFileid() {
        return fileid;
    }

    public void setFileid(Integer fileid) {
        this.fileid = fileid;
    }
}