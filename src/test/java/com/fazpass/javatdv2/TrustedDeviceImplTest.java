package com.fazpass.javatdv2;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrustedDeviceImplTest {

    @Test
    public void testExtract() throws IOException {
        TrustedDeviceImpl trustedDevice = new TrustedDeviceImpl("./private_2.key");
        String encryptedMeta = "hiYcQuKR0iY//l/P6bMyuBs82TuLWzezUEH+jNShs+m2zE3aWSfcmwq4/u4k/tDTvI2WlfBKjUgr8if74ST1p5Z3m81xbycHuGzEmn7UoXUHjQaykR31NpBSugMrX4IBkLPjlvqi9gRtSLvFTOqKVPqh0jyTJ2XqpY7SIucZH0nkSl28rGi2MsA9KRSh92Ecgu++zE/IiFnUWJttGfyurMdGb3j22WfVdA/FPyaJef6eGGUHRctP6mxP6xyC6O90NjKynkk88a9pmSxDoAQ7m1kTW3wWTgHUeTIwrZYQ+XbR7LEx0LAlCO9qa4JlILyvj/Q2eZMaok+VnviHrqSg13kLUHkNoxMMu8syh1XmheyuzT7aX/aq53dtzgZgoIdTaYcQYYt/9XYs+EIvN+7/azai2VuwyzwC3g57M1XXmiNYG+sgENn84qgf9GXMf2zcIDH+QDNOMGFuqkKM/HHBwBK2C5LdI8Esn+r+Jx16SE5CpTDqW8cJyNZKAv2VqlaiP5wRpHrDXqQ1I38JUWVUWSQmdAmiRu+bp7Ub5ydge5KsvGdvuN+pWBPxnu/id6Vfq8vhjp1uVYmbR6cEQjrcRnGfdIqZfSBrg9+Fb0rd5cwjxBngQp6DCMnL/WWCKPoH7Wypo/GqQkDks/wqtU5s4P5DkXnmX95+WGu5FkLTPsQA6M8JSXMhCiTRS+pS+z+jZN6DILpvl2ZsiAQexwvcBsXBWUJt6YogfBin5qXl/hJZ2FdYWLbHTOptCZvxwZyT92pCPHgkv6KAoPhOvMOka0fwYFy+Sj4R6J0r3+CaC7Lqscv3an4KXE38AobXV65hnfWXcObuPzUsepa5NgTWAK7I1T7wUJwe3nzZvdlc87UikExWaJlxGAlWmYNV4Ao5xawMuBEbch4KWXGsnF4K4r+KscdQMYcyye3AXeu81pSK2OmjNHL9fK2sOd3OOVDBpwaSx9ypykHiBKaORQNGGtfKRNg2dDNVNPTzT0yLt/ke0KCel+JXmmFulEqAwXJkmRyCTeJqi3gVwMzq62IWf3KQTe9sK2dOiluo5jFpMjPIVZMCOJWBIAhPwDva7cxQVKSIQO17vuZutxPjkFgr7L5DPMJMYmgstOQXsctIp5CZRGQ/Odt1buQOWeB1JZO4Z4mLcwjK84arjdjIjHggVMaZS3n7Swa0MCyRDzCNB0ZaWZBFTGbai08rqR3nGonDdD9EOyBsva9oxHCyywf0mmiY7Zxcd69UaDttAKl54Oi3RB67fz6qqNw2Tjmg9kBccPAd5H2B31omCYP8S5RQvkubMjRJ2Ki6uTg8rsk1a4G8PUA4Zd1SyD317rYlEGahom27kpxoUZjKYHt+Q/D0UQ==";
        Device device = trustedDevice.extract(encryptedMeta);
        assertEquals("fazpass_id", device.getFazpassId());
    }

    @Test
    public void testExtractWithInvalidPrivateKey() {
        try {
            TrustedDeviceImpl trustedDevice = new TrustedDeviceImpl("invalid/path/to/private_key");
            String meta = "encrypted_meta";
            trustedDevice.extract(meta);
        } catch (Exception e) {
            assertEquals("key not found", e.getMessage());
        }
    }
}