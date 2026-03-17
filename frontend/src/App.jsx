import { useEffect, useState } from "react";
import "./App.css";

function App() {
  const [isLogin, setIsLogin] = useState(false);
  const [user, setUser] = useState(null);
  const [status, setStatus] = useState("loading");

  useEffect(() => {
    fetch("http://localhost:8080/api/user", {
      credentials: "include",
    })
      .then((res) => {
        if (!res.ok) {
          throw new Error("사용자 정보를 불러오지 못했습니다.");
        }
        return res.json();
      })
      .then((data) => {
        if (data.authenticated) {
          setIsLogin(true);
          setUser({
            name: data.name,
            picture: data.picture,
          });
        } else {
          setIsLogin(false);
          setUser(null);
        }
        setStatus("success");
      })
      .catch((err) => {
        console.error(err);
        setStatus("error");
      });
  }, []);

  const handleGoogleLogin = () => {
    window.location.href = "http://localhost:8080/oauth2/authorization/google";
  };

  const handleKakaoLogin = () => {
    window.location.href = "http://localhost:8080/oauth2/authorization/kakao";
  };

  const handleLogout = () => {
    window.location.href = "http://localhost:8080/logout";
  };

  if (status === "loading") {
    return <p>로딩 중...</p>;
  }

  if (status === "error") {
    return <p>에러가 발생했습니다.</p>;
  }

  return (
    <div>
      <h1>Social Login Practice</h1>

      {!isLogin ? (
        <div>
          <button onClick={handleGoogleLogin}>Google 로그인</button>
          <button onClick={handleKakaoLogin}>Kakao 로그인</button>
        </div>
      ) : (
        <div>
          {user?.name && <p>이름: {user.name}</p>}
          {user?.email && <p>이메일: {user.email}</p>}
          {user?.picture && (
            <img src={user.picture} alt="profile" width="100" />
          )}
          <button onClick={handleLogout}>로그아웃</button>
        </div>
      )}
    </div>
  );
}

export default App;
