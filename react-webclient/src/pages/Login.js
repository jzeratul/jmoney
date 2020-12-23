import React, { useState } from 'react';

function Login() {

  const username = useFormInput('');
  const password = useFormInput('');

  // handle button click of login form
  const handleLogin = () => {

  }

  return (
    <div>
      Login<br /><br />
      <div>
        Username<br />
        <input type="text" {...username} autoComplete="new-password" />
      </div>
      <div style={{ marginTop: 10 }}>
        Password<br />
        <input type="password" {...password} autoComplete="new-password" />
      </div>
      <input
        type="button"
        style={{ marginTop: 10 }}
        value="Login"
        onClick={handleLogin} />
    </div>
  );
}

// custom hook to manage the form input
const useFormInput = initialValue => {
  const [value, setValue] = useState(initialValue);

  const handleChange = e => {
    setValue(e.target.value);
  }
  return {
    value,
    onChange: handleChange
  }
}

export default Login;