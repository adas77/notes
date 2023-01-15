import clsx from "clsx"
import { type ButtonHTMLAttributes, type ReactNode } from "react"

interface Props extends ButtonHTMLAttributes<HTMLButtonElement> {
    children: ReactNode
}

const Button = ({
    children,
    ...props }: Props) => {
    return (
        <button
            className={clsx(
                "w-32 h-10",
                "h-fit w-fit rounded-lg text-xs font-medium transition-all",
                "disabled:cursor-not-allowed disabled:opacity-50",
                "dark: text-white",
                "bg-blue-500 border border-brand text-brand hover:border-brand-dark hover:bg-brand-light disabled:border-brand disabled:bg-transparent"
            )}
            {...props}>
            {children}
        </button>
    )
}

export default Button