<script>
export default {
    name: 'MenuItem',
    functional: true,
    props: {
        icon: {
            type: String,
            default: ''
        },
        title: {
            type: String,
            default: ''
        }
    },
    render(h, context) {
        const { icon, title } = context.props;
        const vnodes = [];

        if (icon) {
            vnodes.push(<font-awesome-icon icon={icon} />);
            //vnodes.push(<svg-icon icon-class={icon}/>);
        }

        if (title) {
            vnodes.push(<span style="margin: 15px" slot='title'>{(title)}</span>);
        }
        return vnodes;
    }
};
</script>
