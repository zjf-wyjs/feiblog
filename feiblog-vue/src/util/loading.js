
const api={
    showLoading(obj) {
        obj.$Spin.show({
            render: (h) => {
                return h('div', [
                    h('Icon', {
                        'class': 'demo-spin-icon-load',
                        props: {
                            type: 'ios-loading',
                            size: 18
                        }
                    }),
                    h('div', 'Loading')
                ])
            }
        });

    },
    hideLoading(obj){
        obj.$Spin.hide();
    }
}

export default api;