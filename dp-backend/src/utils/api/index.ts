import { UserAccount, User, Transaction, Card } from '../../types';

const myInit = (method = 'GET', token?: string) => {
  return {
    method,
    headers: {
      'Content-Type': 'application/json',
      'X-Secret-Token': 'from-gateway',
      Authorization: token ? `Bearer ${token}` : '',
    },
    mode: 'cors' as RequestMode,
    cache: 'default' as RequestCache,
  };
};

const myRequest = (endpoint: string, method: string, token?: string) =>
  new Request(endpoint, myInit(method, token));

const baseUrl = 'http://localhost:8081';

const rejectPromise = (response?: Response): Promise<Response> =>
  Promise.reject({
    status: (response && response.status) || '00',
    statusText: (response && response.statusText) || 'Ocurrió un error',
    err: true,
  });

export const login = (email: string, password: string) => {
  return fetch(myRequest(`${baseUrl}/auth-server/api/login`, 'POST'), {
    body: JSON.stringify({ email, password }),
  })
    .then((response) => {
      if (response.ok) {
        return response.json();
      }
      return rejectPromise(response);
    })
    .catch((err) => {
      console.log(err);
      return rejectPromise(err);
    });
};

export const createAnUser = (user: User) => {
  return fetch(myRequest(`${baseUrl}/users-server/api/register`, 'POST'), {
    body: JSON.stringify(user),
  })
    .then((response) => {
      if (response.ok) {
        return response.json(); // Registro exitoso
      }
      return rejectPromise(response); // Manejo de error en el registro
    })
    .then((data) => {
      console.log('Usuario registrado:', data);

      // Llamar a la función de login para obtener el token
      return login(user.email, user.password).then((loginResponse) => {
        console.log('Usuario logueado:', loginResponse);

        // Retornar los datos del usuario y el token
        return { user: data, token: loginResponse.accessToken };
      });
    })
    .catch((err) => {
      console.log('Error en el registro o login:', err);
      return rejectPromise(err);
    });
};

export const logout = (token: string) => {
  return fetch(myRequest(`${baseUrl}/auth-server/api/logout`, 'POST', token))
    .then((response) => {
      if (response.ok) {
        return response.json();
      }
      return rejectPromise(response);
    })
    .catch((err) => {
      console.log(err);
      return rejectPromise(err);
    });
};

export const getUser = (id: string): Promise<User> => {
  return fetch(myRequest(`${baseUrl}/users-server/api/user/${id}`, 'GET'))
    .then((response) =>
      response.ok ? response.json() : rejectPromise(response)
    )
    .catch((err) => {
      console.log(err);
      return rejectPromise(err);
    });
};

export const updateUser = (
  id: string,
  data: any,
  token: string
): Promise<Response> => {
  return fetch(myRequest(`${baseUrl}/users/${id}`, 'PATCH', token), {
    body: JSON.stringify(data),
  })
    .then((response) =>
      response.ok ? response.json() : rejectPromise(response)
    )
    .catch((err) => {
      console.log(err);
      return rejectPromise(err);
    });
};

// TODO: remove this functionality once backend is ready
const generateCvu = (): string => {
  let cvu = '';
  for (let i = 0; i < 22; i++) {
    cvu += Math.floor(Math.random() * 10);
  }
  return cvu;
};

// TODO: remove this functionality once backend is ready
const generateAlias = (): string => {
  const words = [
    'Cuenta',
    'Personal',
    'Banco',
    'Argentina',
    'Digital',
    'Money',
    'House',
    'Bank',
    'Account',
    'Cartera',
    'Wallet',
    'Pago',
    'Pay',
    'Rapido',
    'Seguro',
  ];
  const length = 3;
  let alias = '';
  for (let i = 0; i < length; i++) {
    alias += words[Math.floor(Math.random() * words.length)];
    if (i < length - 1) {
      alias += '.';
    }
  }
  return alias;
};

// TODO: remove this functionality once backend is ready
export const createAnAccount = (data: any): Promise<Response> => {
  const { user, accessToken } = data;
  console.log("Creando cuenta para el usuario:", user);
  const alias = generateAlias();
  const cvu = generateCvu();
  const account = {
    alias,
    cvu,
    balance: 0,
    name: `${user.firstName} ${user.lastName}`,
  };

  return fetch(
    myRequest(`${baseUrl}/users/${user.id}/accounts`, 'POST', accessToken),
    {
      body: JSON.stringify(account),
    }
  ).then((response) =>
    response.ok ? response.json() : rejectPromise(response)
  );
};

export const getAccount = (id: string, token: string): Promise<UserAccount> => {
  return fetch(myRequest(`${baseUrl}/accounts-server/api/accounts/${id}`, 'GET', token), {})
    .then((response) => {
      if (response.ok) {
        return response.json();
      }
      return rejectPromise(response);
    })
    .catch((err) => {
      console.log(err);
      return rejectPromise(err);
    });
};

export const getAccounts = (token: string): Promise<UserAccount[]> => {
  return fetch(myRequest(`${baseUrl}/accounts-server/api/accounts/getAll`, 'GET', token))
    .then((response) =>
      response.ok ? response.json() : rejectPromise(response)
    )
    .catch((err) => {
      console.log(err);
      return rejectPromise(err);
    });
};

export const updateAccount = (
  id: string,
  data: any,
  token: string
): Promise<Response> => {
  return fetch(myRequest(`${baseUrl}/accounts-server/api/accounts/${id}`, 'PATCH', token), {
    body: JSON.stringify(data),
  })
    .then((response) =>
      response.ok ? response.json() : rejectPromise(response)
    )
    .catch((err) => {
      console.log(err);
      return rejectPromise(err);
    });
};

export const getUserActivities = (
  userId: string,
  token: string,
  limit?: number
): Promise<Transaction[]> => {
  return fetch(
    myRequest(
      `${baseUrl}/activities-server/api/${userId}/activty`,
      'GET',
      token
    )
  )
    .then((response) => {
      if (response.ok) {
        return response.json();
      }
      return rejectPromise(response);
    })
    .catch((err) => {
      console.log(err);
      return rejectPromise(err);
    });
};

export const getUserActivity = (
  userId: string,
  activityId: string,
  token: string
): Promise<Transaction> => {
  return fetch(
    myRequest(
      `${baseUrl}/activities-server/api/user/${userId}/activity/${activityId}`,
      'GET',
      token
    )
  )
    .then((response) => {
      if (response.ok) {
        return response.json();
      }
      return rejectPromise(response);
    })
    .catch((err) => {
      console.log(err);
      return rejectPromise(err);
    });
};

export const getUserCards = (
  userId: string,
  token: string
): Promise<Card[]> => {
  return fetch(myRequest(`${baseUrl}/cards-server/api/accounts/${userId}/cards`, 'GET', token))
    .then((response) => {
      if (response.ok) {
        return response.json();
      }
      return rejectPromise(response);
    })
    .catch((err) => {
      console.log(err);
      return rejectPromise(err);
    });
};

export const getUserCard = (userId: string, cardId: string): Promise<Card> => {
  return fetch(myRequest(`${baseUrl}/accounts-server/api/accounts/${userId}/cards/${cardId}`, 'GET'))
    .then((response) => {
      if (response.ok) {
        return response.json();
      }
      return rejectPromise(response);
    })
    .catch((err) => {
      console.log(err);
      return rejectPromise(err);
    });
};

export const deleteUserCard = (
  userId: string,
  cardId: string,
  token: string
): Promise<Response> => {
  return fetch(
    myRequest(`${baseUrl}/cards-server/api/accounts/${userId}/cards/${cardId}`, 'DELETE', token)
  )
    .then((response) => {
      if (response.ok) {
        // No intentar convertir a JSON si no hay contenido
        return Promise.resolve(response);
      }
      return rejectPromise(response); // Manejar el caso de error
    })
    .catch((err) => {
      console.log(err);
      return rejectPromise(err);
    });
};

export const createUserCard = (
  userId: string,
  card: any,
  token: string
): Promise<Response> => {
  return fetch(myRequest(`${baseUrl}/cards-server/api/accounts/${userId}/cards`, 'POST', token), {
    body: JSON.stringify(card),
  })
    .then((response) =>
      response.ok ? response.json() : rejectPromise(response)
    )
    .catch((err) => {
      console.log(err);
      return rejectPromise(err);
    });
};

// TODO: edit when backend is ready
export const createDepositActivity = (
  userId: string,
  amount: number,
  token: string,
  cardNumber: string,
) => {
  const maxAmount = 30000;
  if (amount > maxAmount) return rejectPromise();

  const activity = {
    amount,
    cardNumber,
  };

  return fetch(
    myRequest(`${baseUrl}/activities-server/api/accounts/${userId}/transferences`, 'POST', token),
    {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${token}`,
      },
      body: JSON.stringify(activity),
    }
  )
    .then((response) =>
      response.ok ? response.json() : rejectPromise(response)
    )
    .catch((err) => {
      console.log(err);
      return rejectPromise(err);
    });
};

export const createNewDeposit = (
  userId: string,
  amount: number,
  token: string,
  cardNumber: string,
) => {
  const maxAmount = 30000;
  if (amount > maxAmount) return rejectPromise();

  const activity = {
    amount,
    cardNumber,
  };

  return fetch(
    myRequest(`${baseUrl}/activities-server/api/accounts/${userId}/transferences`, 'POST', token),
    {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'X-Secret-Token': 'from-gateway',
        Authorization: `Bearer ${token}`,
      },
      body: JSON.stringify(activity),
    }
  )
    .then((response) => {
      if (response.ok) {
        return response.json();
      } else {
        return response.text().then((text) => {
          console.error('Server returned an error:', text);
          return rejectPromise(response);  // Obtener detalles del error desde el servidor
        });
      }
    })
    .catch((err) => {
      console.error('Request failed:', err);
      return rejectPromise(err);
    });
};



// TODO: remove when backend is ready
// const depositMoney = (amount: number, userId: string, token: string) => {
//   return getAccount(userId, token)
//     .then((account) => {
//       const newBalance = account.balance + amount;
//       const accountId = account.id;
//       return {
//         newBalance,
//         accountId,
//       };
//     })
//     .then(({ newBalance, accountId }) => {
//       fetch(
//         myRequest(
//           `${baseUrl}/users/${userId}/accounts/${accountId}`,
//           'PATCH',
//           token
//         ),
//         {
//           body: JSON.stringify({ balance: newBalance }),
//         }
//       )
//         .then((response) =>
//           response.ok ? response.json() : rejectPromise(response)
//         )
//         .catch((err) => {
//           console.log(err);
//           return rejectPromise(err);
//         });
//     });
// };

// TODO: edit when backend is ready
export const createTransferActivity = (
  userId: string,
  token: string,
  origin: string,
  destination: string,
  amount: number,
  name?: string
) => {
  return fetch(
    myRequest(`${baseUrl}/activities-server/api/accounts/${userId}/transfer`, 'POST', token),
    {
      body: JSON.stringify({
        type: 'Transfer',
        amount: amount * -1,
        origin,
        destination,
        name,
      }),
    }
  )
    .then((response) =>
      response.ok ? response.json() : rejectPromise(response)
    )
    .catch((err) => {
      console.log(err);
      return rejectPromise(err);
    });
};

// TODO: remove when backend is ready
// const discountMoney = (amount: number, userId: string, token: string) => {
//   return getAccount(userId, token)
//     .then((account) => {
//       // amount is negavite
//       const newBalance = account.balance + amount;
//       const accountId = account.id;
//       return {
//         newBalance,
//         accountId,
//       };
//     })
//     .then(({ newBalance, accountId }) => {
//       fetch(
//         myRequest(
//           `${baseUrl}/users/${userId}/accounts/${accountId}`,
//           'PATCH',
//           token
//         ),
//         {
//           body: JSON.stringify({ balance: newBalance }),
//         }
//       )
//         .then((response) =>
//           response.ok ? response.json() : rejectPromise(response)
//         )
//         .catch((err) => {
//           console.log(err);
//           return rejectPromise(err);
//         });
//     });
// };

export const getUserRecentActivities = (
  userId: string,
  token: string,
  limit?: number
): Promise<Transaction[]> => {
  return fetch(
    myRequest(
      `${baseUrl}/activities-server/api/${userId}/transferences${limit ? `?_limit=${limit}` : ''}`,
      'GET',
      token
    )
  )
    .then((response) => {
      if (response.ok) {
        return response.json();
      }
      return rejectPromise(response);
    })
    .catch((err) => {
      console.log(err);
      return rejectPromise(err);
    });
};