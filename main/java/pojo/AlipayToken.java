package pojo;

public class AlipayToken {
    private String AuthToken;
    private String RefreshToken;

    public AlipayToken(String authToken, String refreshToken) {
        AuthToken = authToken;
        RefreshToken = refreshToken;
    }

    public String getAuthToken() {
        return AuthToken;
    }

    public String getRefreshToken() {
        return RefreshToken;
    }
}
