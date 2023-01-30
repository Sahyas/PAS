import {
    Alert,
    AlertTitle,
    Box,
    Button,
    Paper,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TablePagination,
    TableRow,
    TextField,
    Typography,
  } from "@mui/material";
import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import useSWR from "swr";

function Profile() {
    const [oldPassword, setOldPassword] = useState("");
    const [password, setPassword] = useState("");
    const [repeatedPassword, setRepeatedPassword] = useState("");
    const [user] = 
    useState(JSON.parse(localStorage.getItem("user")))
    const [isPasswordValid] = useState("Wrong password")
    const [errorMessage, setErrorMessage] = useState();

    function handleLogout(event) {
        localStorage.clear();
    }

    function handlePasswordChange(event) {
        if (password !== repeatedPassword) {
            alert("Wrong passwords")
        } else {
            const passwordChangeData = {
                currentPassword: oldPassword,
                newPassword: password,
            };

            //axios.zmianaHasła

        }
    }

    //return ( profil );
}

export default Profile;