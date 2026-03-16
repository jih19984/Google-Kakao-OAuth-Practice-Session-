import "./App.css";

function App() {
  const isLogin = true;

  const user = {
    name: "홍길동",
    email: "hong@example.com",
    picture: "https://via.placeholder.com/100",
    provider: "google",
  };

  const handleGoogleLogin = () => {
    window.location.href = "http://localhost:8080/oauth2/authorization/google";
  };

  const handleKakaoLogin = () => {
    window.location.href = "http://localhost:8080/oauth2/authorization/kakao";
  };

  const handleLogout = () => {
    window.location.href = "http://localhost:8080/logout";
  };

  return (
    <div className="container">
      <h1>Social Login Practice</h1>

      {!isLogin ? (
        <div className="login-box">
          <p>로그인 버튼을 눌러주세요.</p>
          <button className="google-btn" onClick={handleGoogleLogin}>
            Google 로그인
          </button>
          <button className="kakao-btn" onClick={handleKakaoLogin}>
            Kakao 로그인
          </button>
        </div>
      ) : (
        <div className="user-card">
          <h2>사용자 정보</h2>
          <img className="profile-img" src={user.picture} alt="profile" />
          <p>
            <strong>이름:</strong> {user.name}
          </p>
          <p>
            <strong>이메일:</strong> {user.email}
          </p>
          <p>
            <strong>로그인 제공자:</strong> {user.provider}
          </p>
          <button className="logout-btn" onClick={handleLogout}>
            로그아웃
          </button>
        </div>
      )}
    </div>
  );
}

export default App;
