package top.crazycat.medical.web.daosupport;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2018/3/20
 * description:
 */
public class BankDO implements Serializable {
    private static final long serialVersionUID = -8797316687572461576L;
    private Long seq;
    private String bankId;
    private String bankName;
    private String bankStaticImg;
    private String validFlg;
    private String remark;
    private String delFlg;

    @Override
    public String toString() {
        return "BankDO{" +
                "seq=" + seq +
                ", bankId='" + bankId + '\'' +
                ", bankName='" + bankName + '\'' +
                ", bankStaticImg='" + bankStaticImg + '\'' +
                ", validFlg='" + validFlg + '\'' +
                ", remark='" + remark + '\'' +
                ", delFlg='" + delFlg + '\'' +
                '}';
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankStaticImg() {
        return bankStaticImg;
    }

    public void setBankStaticImg(String bankStaticImg) {
        this.bankStaticImg = bankStaticImg;
    }

    public String getValidFlg() {
        return validFlg;
    }

    public void setValidFlg(String validFlg) {
        this.validFlg = validFlg;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDelFlg() {
        return delFlg;
    }

    public void setDelFlg(String delFlg) {
        this.delFlg = delFlg;
    }
}
