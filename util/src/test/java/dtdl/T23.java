package dtdl;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huqinsong
 * @date 2018/10/23
 */
public class T23 {

    public static void main(String[] args) {
        ChnlBalanceInfo info = new ChnlBalanceInfo();
        info.addBalance(9l);
        info.addBalance(9l);
        System.out.println(info.getD0WithdrawBalance());

    }



    private static class ChnlBalanceInfo {
        /** 渠道账号id */
        Integer accountId;
        /** 渠道账号可用余额（本地计算） */
        long d0WithdrawBalance;


        public Integer getAccountId() {
            return accountId;
        }

        public void setAccountId(Integer accountId) {
            this.accountId = accountId;
        }

        public long getD0WithdrawBalance() {
            return d0WithdrawBalance;
        }

        public void setD0WithdrawBalance(long d0WithdrawBalance) {
            this.d0WithdrawBalance = d0WithdrawBalance;
        }

        public void addBalance(long d0WithdrawBalance) {
            this.d0WithdrawBalance += d0WithdrawBalance;
        }
    }
}
