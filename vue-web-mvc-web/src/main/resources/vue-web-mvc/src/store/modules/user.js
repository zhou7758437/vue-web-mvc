// initial state
// shape: [{ id, quantity }]
const state = {
  user: null,
}
const getters = {
  hasRole: (state) => (role) => {
    if (!state.user) {
      return false;
    }

    console.log(state);

    var roles=state.user.roles;

    if(!roles){
      return false;
    }

    for(var k in roles){
      if(k.toLowerCase()==role.toLowerCase()){
        return !!roles[k];
      }
    }
    return false;

  }
}
const actions = {
  updateUser ({ commit, state }, user) {
    commit('updateUser', user)
  },

}
// mutations
const mutations = {
  updateUser(state, user) {
    state.user = user;
  },
}

export default {
  state,
  getters,
  actions,
  mutations
}
