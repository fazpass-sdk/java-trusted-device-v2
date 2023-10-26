package com.fazpass.javatdv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.PrivateKey;
class UtilsTest {
    private String privateKey = "";
    private Utils utils;

    @BeforeEach
    void setUp() {
        try {
            privateKey = readKeyFromFile();
            utils = new Utils(privateKey);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private String readKeyFromFile() throws IOException {
        return new String(Files.readAllBytes(Paths.get("./private_2.key")));
    }
    @Test
    public void testGetPrivateKey() throws Exception {
        PrivateKey privateKey = utils.getPrivateKey(this.privateKey);
        Assertions.assertNotNull(privateKey);
    }

    @Test
    public void testGetPrivateKeyError() {
        try {
            utils.getPrivateKey(this.privateKey.substring(0, 120));
        }catch (Exception e){
            Assertions.assertEquals("error generating private key",e.getMessage());
        }
    }

    @Test
    public void testDecryptResponse() {
        String newMeta = "NlvZpR44O4TOMCYTRsBLHKMVZLucupg2NJVI4Kc2/4EP/Ml9ckHLZXi/XAmIOdgChU3rJhEf8JB5jAXA5I6j8+ls+jIpF13sU+IeP2PezBX5zYYMhWI5NVIdYSefGFFBOKEceLNqgMybpTneRm98o3mdbJrKg4cAJJDAhbeNLRBHD1XZFj8SCMaupfuxY/5I09oia4ms3/eikIwLO6w1lUZT0Wo9JHr2bZM14JhWVm3qHMNVMp8dceS+SMvRa7dnLH1Ca7dD+Zxq9gfIWO73ULAWHMHtWLc3d+6Kfip7eYmHborpFBG3NU4ZBC3NC7L5FPlndsU8m2ODybzkNWAIPbBRz5q3gKVGorJUs4fIK71myEcoTOwHPvHpcpGeKXSKQgyb9uu3ZOXMKZVDXGDMXUH5UqcRz/jWz937Erabo08L8AFtzNkG8AQwaLzthApNtlPOfpajYfgKGydqv/x4dBHvZ3YmPhQl1uAuaRXMUi/ceHzqn8r0X2iT20I8r3ilauujQgFRK+RVY86n/xi/fVpDem9KvpFXSoaJBJM3WxqrFIc2ZvRGItR7AraEoCE404nPgC9DltOCq+50X5cNYIyeqZJO7UkiW0NjmX1DySPmvcTev6M7aW8QCpoXLDehaEGyAdaHiMK+fh/r+p5ELnth3zztpAanCFBo3r6OoPgA14QA5myUqVW1ppcH21Ukc3hEZBWFQMMeQPpjIjG3PVSHshpcE7gQiiYPI3DuHodmf5JvXDsB0AbaMbeKpiNTsBvzp9ZWiJ+ucBfjS0nmOrtVbkw1lLc6gvkm6vl71riS5yPZ7yK1JbfNHMvMZfDhOF8ZDraSZdg8uVj1BKcF2fSqayCmnnLbWE54jIdS6GlNP9o79qGOH/QfVLLYfZbS30Q9eaJr4WqZVewFe/0LoSKEQj4r20+C37dv1T21AuzQ19kMu77GM+GC5GeXYJnykSTKA5qRrrqJ24JsDFe/MucxVFomdinuqqTjmMq/yUVxxOWf7H/Z7Hb1jB913UjEvEwAklRRv0WPTsmkcS78nLHVVq+jKBNNJS3Ek/IwHAkb7+Mz57g5fLWG7kPA6fjLM9sCnQWn+RtbL4+5tEYpeQYdYIMv2S6CRgHndI/oq/l8hzGXaUd2Eo/zUMXPVOFbfKDgcx4XZWBiTBAyU7BqSW59HQ2bRElfOvkNpnl7I+xI6OiXIJ1yuYnF1HqqP3NTXCRrO3vZKyOw9QpY/+Y+5wQr81sXMeccBNnxNe0M4dLMEEIRXwpBm3Cbbbf117auDFaViaWDwUsO13YfO7KPgEOx+MUhWPT3nh8G2KiwadquhgexTAVgxZJfdrPQAV+e80yDyIX0P+DPh+RB48+xFw==";
        // String encryptedMeta = "hiYcQuKR0iY//l/P6bMyuBs82TuLWzezUEH+jNShs+m2zE3aWSfcmwq4/u4k/tDTvI2WlfBKjUgr8if74ST1p5Z3m81xbycHuGzEmn7UoXUHjQaykR31NpBSugMrX4IBkLPjlvqi9gRtSLvFTOqKVPqh0jyTJ2XqpY7SIucZH0nkSl28rGi2MsA9KRSh92Ecgu++zE/IiFnUWJttGfyurMdGb3j22WfVdA/FPyaJef6eGGUHRctP6mxP6xyC6O90NjKynkk88a9pmSxDoAQ7m1kTW3wWTgHUeTIwrZYQ+XbR7LEx0LAlCO9qa4JlILyvj/Q2eZMaok+VnviHrqSg13kLUHkNoxMMu8syh1XmheyuzT7aX/aq53dtzgZgoIdTaYcQYYt/9XYs+EIvN+7/azai2VuwyzwC3g57M1XXmiNYG+sgENn84qgf9GXMf2zcIDH+QDNOMGFuqkKM/HHBwBK2C5LdI8Esn+r+Jx16SE5CpTDqW8cJyNZKAv2VqlaiP5wRpHrDXqQ1I38JUWVUWSQmdAmiRu+bp7Ub5ydge5KsvGdvuN+pWBPxnu/id6Vfq8vhjp1uVYmbR6cEQjrcRnGfdIqZfSBrg9+Fb0rd5cwjxBngQp6DCMnL/WWCKPoH7Wypo/GqQkDks/wqtU5s4P5DkXnmX95+WGu5FkLTPsQA6M8JSXMhCiTRS+pS+z+jZN6DILpvl2ZsiAQexwvcBsXBWUJt6YogfBin5qXl/hJZ2FdYWLbHTOptCZvxwZyT92pCPHgkv6KAoPhOvMOka0fwYFy+Sj4R6J0r3+CaC7Lqscv3an4KXE38AobXV65hnfWXcObuPzUsepa5NgTWAK7I1T7wUJwe3nzZvdlc87UikExWaJlxGAlWmYNV4Ao5xawMuBEbch4KWXGsnF4K4r+KscdQMYcyye3AXeu81pSK2OmjNHL9fK2sOd3OOVDBpwaSx9ypykHiBKaORQNGGtfKRNg2dDNVNPTzT0yLt/ke0KCel+JXmmFulEqAwXJkmRyCTeJqi3gVwMzq62IWf3KQTe9sK2dOiluo5jFpMjPIVZMCOJWBIAhPwDva7cxQVKSIQO17vuZutxPjkFgr7L5DPMJMYmgstOQXsctIp5CZRGQ/Odt1buQOWeB1JZO4Z4mLcwjK84arjdjIjHggVMaZS3n7Swa0MCyRDzCNB0ZaWZBFTGbai08rqR3nGonDdD9EOyBsva9oxHCyywf0mmiY7Zxcd69UaDttAKl54Oi3RB67fz6qqNw2Tjmg9kBccPAd5H2B31omCYP8S5RQvkubMjRJ2Ki6uTg8rsk1a4G8PUA4Zd1SyD317rYlEGahom27kpxoUZjKYHt+Q/D0UQ==";
        Meta device = utils.decryptResponse(newMeta);
        Assertions.assertEquals("", device.getFazpassId());
    }

    @Test
    public void testDecryptResponseError() {
        String newMeta = "NlvZpR44O4TOMCYTRsBLHKMVZLucupg2NJVI4Kc2/4EP/Ml9ckHLZXi/XAmIOdgChU3rJhEf8JB5jAXA5I6j8+ls+jIpF13sU+IeP2PezBX5zYYMhWI5NVIdYSefGFFBOKEceLNqgMybpTneRm98o3mdbJrKg4cAJJDAhbeNLRBHD1XZFj8SCMaupfuxY/5I09oia4ms3/eikIwLO6w1lUZT0Wo9JHr2bZM14JhWVm3qHMNVMp8dceS+SMvRa7dnLH1Ca7dD+Zxq9gfIWO73ULAWHMHtWLc3d+6Kfip7eYmHborpFBG3NU4ZBC3NC7L5FPlndsU8m2ODybzkNWAIPbBRz5q3gKVGorJUs4fIK71myEcoTOwHPvHpcpGeKXSKQgyb9uu3ZOXMKZVDXGDMXUH5UqcRz/jWz937Erabo08L8AFtzNkG8AQwaLzthApNtlPOfpajYfgKGydqv/x4dBHvZ3YmPhQl1uAuaRXMUi/ceHzqn8r0X2iT20I8r3ilauujQgFRK+RVY86n/xi/fVpDem9KvpFXSoaJBJM3WxqrFIc2ZvRGItR7AraEoCE404nPgC9DltOCq+50X5cNYIyeqZJO7UkiW0NjmX1DySPmvcTev6M7aW8QCpoXLDehaEGyAdaHiMK+fh/r+p5ELnth3zztpAanCFBo3r6OoPgA14QA5myUqVW1ppcH21Ukc3hEZBWFQMMeQPpjIjG3PVSHshpcE7gQiiYPI3DuHodmf5JvXDsB0AbaMbeKpiNTsBvzp9ZWiJ+ucBfjS0nmOrtVbkw1lLc6gvkm6vl71riS5yPZ7yK1JbfNHMvMZfDhOF8ZDraSZdg8uVj1BKcF2fSqayCmnnLbWE54jIdS6GlNP9o79qGOH/QfVLLYfZbS30Q9eaJr4WqZVewFe/0LoSKEQj4r20+C37dv1T21AuzQ19kMu77GM+GC5GeXYJnykSTKA5qRrrqJ24JsDFe/MucxVFomdinuqqTjmMq/yUVxxOWf7H/Z7Hb1jB913UjEvEwAklRRv0WPTsmkcS78nLHVVq+jKBNNJS3Ek/IwHAkb7+Mz57g5fLWG7kPA6fjLM9sCnQWn+RtbL4+5tEYpeQYdYIMv2S6CRgHndI/oq/l8hzGXaUd2Eo/zUMXPVOFbfKDgcx4XZWBiTBAyU7BqSW59HQ2bRElfOvkNpnl7I+xI6OiXIJ1yuYnF1HqqP3NTXCRrO3vZKyOw9QpY/+Y+5wQr81sXMeccBNnxNe0M4dLMEEIRXwpBm3Cbbbf117auDFaViaWDwUsO13YfO7KPgEOx+MUhWPT3nh8G2KiwadquhgexTAVgxZJfdrPQAV+e80yDyIX0P+DPh+RB48+xFw==";
        // String encryptedMeta = "hiYcQuKR0iY//l/P6bMyuBs82TuLWzezUEH+jNShs+m2zE3aWSfcmwq4/u4k/tDTvI2WlfBKjUgr8if74ST1p5Z3m81xbycHuGzEmn7UoXUHjQaykR31NpBSugMrX4IBkLPjlvqi9gRtSLvFTOqKVPqh0jyTJ2XqpY7SIucZH0nkSl28rGi2MsA9KRSh92Ecgu++zE/IiFnUWJttGfyurMdGb3j22WfVdA/FPyaJef6eGGUHRctP6mxP6xyC6O90NjKynkk88a9pmSxDoAQ7m1kTW3wWTgHUeTIwrZYQ+XbR7LEx0LAlCO9qa4JlILyvj/Q2eZMaok+VnviHrqSg13kLUHkNoxMMu8syh1XmheyuzT7aX/aq53dtzgZgoIdTaYcQYYt/9XYs+EIvN+7/azai2VuwyzwC3g57M1XXmiNYG+sgENn84qgf9GXMf2zcIDH+QDNOMGFuqkKM/HHBwBK2C5LdI8Esn+r+Jx16SE5CpTDqW8cJyNZKAv2VqlaiP5wRpHrDXqQ1I38JUWVUWSQmdAmiRu+bp7Ub5ydge5KsvGdvuN+pWBPxnu/id6Vfq8vhjp1uVYmbR6cEQjrcRnGfdIqZfSBrg9+Fb0rd5cwjxBngQp6DCMnL/WWCKPoH7Wypo/GqQkDks/wqtU5s4P5DkXnmX95+WGu5FkLTPsQA6M8JSXMhCiTRS+pS+z+jZN6DILpvl2ZsiAQexwvcBsXBWUJt6YogfBin5qXl/hJZ2FdYWLbHTOptCZvxwZyT92pCPHgkv6KAoPhOvMOka0fwYFy+Sj4R6J0r3+CaC7Lqscv3an4KXE38AobXV65hnfWXcObuPzUsepa5NgTWAK7I1T7wUJwe3nzZvdlc87UikExWaJlxGAlWmYNV4Ao5xawMuBEbch4KWXGsnF4K4r+KscdQMYcyye3AXeu81pSK2OmjNHL9fK2sOd3OOVDBpwaSx9ypykHiBKaORQNGGtfKRNg2dDNVNPTzT0yLt/ke0KCel+JXmmFulEqAwXJkmRyCTeJqi3gVwMzq62IWf3KQTe9sK2dOiluo5jFpMjPIVZMCOJWBIAhPwDva7cxQVKSIQO17vuZutxPjkFgr7L5DPMJMYmgstOQXsctIp5CZRGQ/Odt1buQOWeB1JZO4Z4mLcwjK84arjdjIjHgg";
        Meta device = utils.decryptResponse(newMeta);
        Assertions.assertNotNull(device.getFazpassId());
    }

}
