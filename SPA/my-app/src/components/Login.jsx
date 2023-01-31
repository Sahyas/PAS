import {
  Container,
  CssBaseline,
  Box,
  Typography,
  TextField,
  Button,
  Alert,
  AlertTitle,
} from "@mui/material";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import axios from "axios";

const theme = createTheme();

function Login() {
    const [login, setLogin] = useState('');
    const [password, setPassword] = useState('');
    const [errorMessage, setErrorMessage] = useState();
    const navigate = useNavigate();

    function handleSubmit(event) {
        event.preventDefault();
        const credentials = {
            login,
            password
        };

        axios
        .post(`/auth/login`, credentials)
        .then( res => {
          axios.defaults.headers.common["Authorization"] = `Bearer ${res.data}`;
          localStorage.setItem("jwtToken", `Bearer ${res.data}`);
          //localStorage.setItem("user", JSON.stringify(res.data.userData));
          console.log(localStorage);
          navigate(`/profile`)
        }).catch( err => {
          setErrorMessage(err.response.data)
        });
    }

    return (
        <ThemeProvider theme={theme}>
      <Container component="main" maxWidth="xs">
        <CssBaseline />
        <Box
          sx={{
            marginTop: 8,
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
          }}
        >
          {errorMessage && (
          <Alert
            severity="error"
            onClose={() => {
              setErrorMessage(null);
            }}
          >
            <AlertTitle>Error</AlertTitle>
            <strong>{errorMessage}</strong>
          </Alert>
        )}
          <Typography component="h2" variant="h7">
            Sign in
          </Typography>
          <Box
            component="form"
            onSubmit={handleSubmit}
            noValidate
            sx={{ mt: 1 }}
          >
            <TextField
              margin="normal"
              required
              fullWidth
              id="login"
              label="Login"
              name="login"
              autoComplete="login"
              autoFocus
              onChange={(event) => setLogin(event.target.value)}
            />
            <TextField
              margin="normal"
              required
              fullWidth
              name="password"
              label="Password"
              type="password"
              id="password"
              autoComplete="current-password"
              onChange={(event) => setPassword(event.target.value)}
            />

            <Button
              color="success"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
              onClick={handleSubmit}
            >
              Sign In
            </Button>
          </Box>
        </Box>
      </Container>
    </ThemeProvider>
      );

}

export default Login;