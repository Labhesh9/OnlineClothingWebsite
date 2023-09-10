import { createSlice } from "@reduxjs/toolkit";

import {
  addCartToDataBase,
  findUpdateCartByProductIDInDataBase,
} from "../components/funtion/Ulit";

const initialState = {
  isCartOpen: false,
  orders: [],
  cart: [],
  items: [],
  item: {
    id: 0,
    agetypesId: 0,
    categoryId: 0,
    imageproduct: "",
    name: "",
    nameDetail: "",
    originalPrice: "",
  },
};

export const cartSlice = createSlice({
  name: "cart",
  initialState,
  reducers: {
    setItem: (state, action) => {
      state.item = action.payload;
    },

    setItems: (state, action) => {
      state.items = action.payload;
    },
    loadItemsCart: (state, action) => {
      state.cart = action.payload;
    },
    setOrders: (state, action) => {
      state.orders = action.payload;
    },
    loadOrders: (state, action) => {
      state.orders = action.payload;
    },
    addToCart: (state, action) => {
      //state.cart = [...state.cart, action.payload.item];
      const temp = state.cart.find((a) => {
        if (a.id === action.payload.item.id) return true;
        else return false;
      });

      if (temp !== undefined) {
        state.cart = state.cart.map((item) => {
          if (item.id === action.payload.item.id) {
            return {
              ...item,
              quantity: item.quantity + action.payload.item.quantity,
            };
          } else {
            return item;
          }
        });
      } else {
        state.cart = [...state.cart, action.payload.item];
      }
      // const temp = state.cart.map((item) => {
      //   if (item.id !== action.payload.item.id) {
      //     // No change
      //     return item;
      //   } else {
      //     // Return a new circle 50px below
      //     return {
      //       ...item,
      //       quantity: action.payload.item.quantity,
      //     };
      //   }
      // });
      //console.log("state.cart");
      //console.log(state.cart);
      sessionStorage.setItem("cart", JSON.stringify(state.cart));
      findUpdateCartByProductIDInDataBase(action.payload.item);
    },

    removeFromCart: (state, action) => {
      state.cart = state.cart.filter((item) => item.id !== action.payload.id);
      sessionStorage.setItem("cart", JSON.stringify(state.cart));
    },
    removeAllFromCart: (state, action) => {
      state.cart = [];
      sessionStorage.setItem("cart", JSON.stringify(state.cart));
    },

    increaseCount: (state, action) => {
      state.cart = state.cart.map((item) => {
        if (item.id === action.payload.id) {
          item.quantity++;
        }
        return item;
      });
    },

    decreaseCount: (state, action) => {
      state.cart = state.cart.map((item) => {
        if (item.id === action.payload.id && item.quantity > 1) {
          item.quantity--;
        }
        return item;
      });
    },

    setIsCartOpen: (state) => {
      state.isCartOpen = !state.isCartOpen;
    },
  },
});

export const {
  setItems,
  setItem,
  addToCart,
  loadItemsCart,
  removeFromCart,
  removeAllFromCart,
  increaseCount,
  loadOrders,
  setOrders,
  decreaseCount,
  setIsCartOpen,
} = cartSlice.actions;

export default cartSlice.reducer;
