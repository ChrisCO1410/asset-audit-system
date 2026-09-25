const API_BASE_URL = 'http://localhost:8080/api';

export const login = async (username, password) => {
    const response = await fetch(`${API_BASE_URL}/auth/login`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ username, password }),
    });

    if (!response.ok) {
        throw new Error('Invalid credentials');
    }

    const data = await response.json();
    if (data.token) {
        localStorage.setItem('token', data.token);
    }
    return data;
};

export const getAssets = async () => {
    const token = localStorage.getItem('token');
    const response = await fetch(`${API_BASE_URL}/assets`, {
        headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json',
        },
    });

    if (!response.ok) {
        throw new Error('Failed to fetch assets');
    }

    return response.json();
};